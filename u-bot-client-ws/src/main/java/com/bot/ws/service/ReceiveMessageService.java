package com.bot.ws.service;

import org.springframework.stereotype.Component;

@Component
public interface ReceiveMessageService {
	
	public String processCommand(String message);

}
