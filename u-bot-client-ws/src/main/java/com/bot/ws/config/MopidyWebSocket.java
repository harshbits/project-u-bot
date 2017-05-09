package com.bot.ws.config;

public class MopidyWebSocket{ 
//extends MopidySocket {
//	private final URI uri;
//	private MopidyWebSocket.WebSocket webSocket;
//
//	public MopidyWebSocket(URI uri) {
//		this.uri = uri;
//	}
//
//	@Override
//	protected void connect() {
//		if (webSocket != null) {
//			webSocket.close();
//		}
//
//		webSocket = new MopidyWebSocket.WebSocket(uri);
//		webSocket.connect();
//	}
//
//	@Override
//	protected void disconnect() {
//		if (webSocket != null) {
//			webSocket.close();
//		}
//	}
//
//	@Override
//	public void sendData(String data) {
//		if (webSocket != null) {
//			webSocket.send(data);
//		}
//	}
//
//	public class WebSocket extends WebSocketClient {
//		
//
//		public WebSocket(URI serverURI) {
//			super(serverURI);
//		}
//
//		@Override
//		public void onOpen(ServerHandshake handshakedata) {
//			onConnected();
//		}
//
//		@Override
//		public void onMessage(String message) {
//			onDataReceived(message);
//		}
//
//		@Override
//		public void onClose(int code, String reason, boolean remote) {
//			onDisconnected(false);
//		}
//
//		@Override
//		public void onError(Exception ex) {
//			MopidyWebSocket.this.onError(ex);
//		}
//	}
}