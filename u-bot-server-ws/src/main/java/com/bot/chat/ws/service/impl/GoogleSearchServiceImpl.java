package com.bot.chat.ws.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bot.chat.ws.beans.WebhookRequest;
import com.bot.chat.ws.service.GoogleSearchService;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

public class GoogleSearchServiceImpl implements GoogleSearchService{
	
	@Autowired
	private GoogleCredential credential;

	@Override
	public String handleRequest(WebhookRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
