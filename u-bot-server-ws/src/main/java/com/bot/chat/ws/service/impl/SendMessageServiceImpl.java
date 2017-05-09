package com.bot.chat.ws.service.impl;

import com.bot.chat.ws.service.SendMessageService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendMessageServiceImpl implements SendMessageService {

	
	@Override
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
