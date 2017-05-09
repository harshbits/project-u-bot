package com.bot.ws.service.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.bot.ws.mopidy.client.Mopidy;
import com.bot.ws.mopidy.client.MopidySocket;

public class MopidyClientWebSocketHandler extends TextWebSocketHandler {

	private WebSocketSession session;
	
	private Mopidy spotifyPlayer;

	private static final Logger logger = LoggerFactory.getLogger(MopidyClientWebSocketHandler.class);

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		this.session = session;
		this.spotifyPlayer = mopidyClient();
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		logger.info("Received Message: " + message);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {

	}

	public WebSocketSession getSession() {
		return session;
	}

	public Mopidy getSpotifyPlayer(){
		return spotifyPlayer;
	}
	
	public MopidySocket mopidySocket() {
		MopidySocket socket = new MopidySocket() {

			@Override
			protected void connect() {
				if(session.isOpen()){
				}else{
					logger.error("Failed to connect Spotify Player.");
				}
			}

			@Override
			protected void disconnect() {
				try {
					if(session.isOpen()){
						session.close();
						logger.info("Spotify Player Disconnected");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void sendData(String data) {
				try {
					session.sendMessage(new TextMessage(data));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		return socket;
	}
	
	public Mopidy mopidyClient(){
		try {
			Mopidy mopidy = new Mopidy(mopidySocket());
			mopidy.connect();
			logger.info("Spotify Player Initialized.");
			return mopidy;
		} catch (Exception e) {
			logger.error("{}", e);
			return null;
		}
	}
}
