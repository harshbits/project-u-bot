package com.bot.chat.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.chat.ws.beans.WebhookRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

/**
 * Google custom search service
 * 
 * @author harshbhavsar
 *
 */
@Service
public class GoogleSearchService{
	
	@Autowired
	private GoogleCredential credential;

	public String handleRequest(WebhookRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
