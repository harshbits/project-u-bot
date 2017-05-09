package com.bot.chat.ws.service.impl;

import java.io.FileInputStream;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.auth.oauth2.GoogleCredentials;

public class Test {

	public static void main(String[] args){
		
		String email = "336242486655-compute@developer.gserviceaccount.com";
		String file = "/Users/harshbhavsar/Documents/ids-redis-ws/bot-chat-server-ws/src/main/resources/u-bot-client-auth.p12";
//		List<String> SCOPES = Arrays.asList(searchS);
		
		try{
			HttpTransport httpTransport = new NetHttpTransport();
	        JacksonFactory jsonFactory = new JacksonFactory();
	        
	        GoogleCredentials creds = null;

			GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(
					"/Users/harshbhavsar/Documents/ids-redis-ws/bot-chat-server-ws/src/main/resources/u-bot-client-auth.json"))
					.createScoped(Collections.singleton("https://www.googleapis.com/auth/cse"));
							
			credential.refreshToken();
	        Customsearch search1 = new Customsearch.Builder(httpTransport, jsonFactory, null)
	        		.setApplicationName("u-bot-search")
	        		.setHttpRequestInitializer(credential).build();
	        
//	        CustomsearchRequest<>
//	        search1.getc >
	        
	        System.out.println(search1.cse().list("tomato soup").setCx("018074920225777426056:fiofdmq5jfs").execute());
//	        Customsea
	        
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
}
