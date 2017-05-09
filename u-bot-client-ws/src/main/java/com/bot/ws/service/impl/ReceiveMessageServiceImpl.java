package com.bot.ws.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bot.ws.service.ReceiveMessageService;
import com.bot.ws.service.TextResponseClientService;

public class ReceiveMessageServiceImpl implements ReceiveMessageService {

	private static final Logger logger = LoggerFactory.getLogger(ReceiveMessageServiceImpl.class);
	
//	@Autowired
//	private AppProperties properties;
	
	@Autowired
	private TextResponseClientService service;
	
	@Override
	public String processCommand(String message) {

		logger.info("Incoming message/command input: "+ message);
		String response = service.getParsedResponse(message);
		logger.info("Incoming message/command response: "+ response);
		return response;
	}

}
