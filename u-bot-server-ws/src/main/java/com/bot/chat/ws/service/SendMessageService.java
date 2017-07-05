package com.bot.chat.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.chat.ws.config.AppProperties.Properties;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.extern.slf4j.Slf4j;

/**
 * Send SMS to user phone number
 * 
 * @author harshbhavsar
 *
 */
@Service
@Slf4j
public class SendMessageService {

	@Autowired
	private Properties properties;

	/**
	 * Initial constructor with authentication
	 * 
	 */
	public SendMessageService() {
		Twilio.init(properties.getAccountSid(), properties.getAuthToken());
	}

	/**
	 * Send message(SMS) to user
	 * 
	 * @param message
	 */
	public void sendMessage(String message) {
		try {
			Message m = Message
					.creator(new PhoneNumber("+14694070782"), // to
							new PhoneNumber("+14692100794"), // from
							"Where's Wallace?")
					// .setMediaUrl(new
					// URI("https://static0.twilio.com/recources/logos/twilio-loco-circle-50x50.png"))
					.create();
			log.info("Date Created: {}", m.getDateCreated());
			log.info("Data Sent: {}", m.getDateSent());
		} catch (Exception e) {
			log.error("Send message data error: {}", e);
		}
	}

}
