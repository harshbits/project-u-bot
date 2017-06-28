package com.bot.chat.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.chat.ws.config.AppProperties;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SendMessageService{

	@Autowired
	private AppProperties properties;
	
	public SendMessageService() {
		Twilio.init(properties.getAccountSid(), properties.getAuthToken());
	}
	
	public void sendMessage(String message) {
		
		try {
			Message m = Message
			        .creator(new PhoneNumber("+14694070782"),  // to
			                 new PhoneNumber("+14692100794"),  // from
			                 "Where's Wallace?")
//			        .setMediaUrl(new URI("https://static0.twilio.com/recources/logos/twilio-loco-circle-50x50.png"))
			        .create();
			System.out.println(m.getDateCreated());
			System.out.println(m.getDateSent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
