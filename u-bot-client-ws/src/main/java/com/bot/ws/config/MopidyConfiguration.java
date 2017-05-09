package com.bot.ws.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.jetty.JettyWebSocketClient;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import com.bot.ws.service.impl.MopidyClientWebSocketHandler;

@Configuration
@EnableWebSocket
public class MopidyConfiguration {

	@Autowired
	private AppProperties properties;

	@Bean
	public WebSocketConnectionManager connectionManager() {
		WebSocketConnectionManager manager = new WebSocketConnectionManager(client(), handler(),
				properties.getMopidyWebSocketUrl());
		manager.setAutoStartup(true);
		return manager;
	}

	@Bean
	public JettyWebSocketClient client() {
		return new JettyWebSocketClient();
	}

	@Bean
	public MopidyClientWebSocketHandler handler() {
		return new MopidyClientWebSocketHandler();
	}
	
}
