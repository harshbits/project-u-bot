package com.bot.ws.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.bot.ws.config.IntentConfiguration.IgnoreIntents;
import com.bot.ws.config.IntentConfiguration.MusicIntents;
import com.bot.ws.service.PinControlService;
import com.bot.ws.service.ReceiveMessageService;
import com.bot.ws.service.SendMessageService;
import com.bot.ws.service.SmartHomeControlService;
import com.bot.ws.service.SpotifyService;
import com.bot.ws.service.TextResponseClientService;
import com.bot.ws.service.VoiceResponseClientService;
import com.bot.ws.service.impl.PinControlServiceImpl;
import com.bot.ws.service.impl.ReceiveMessageServiceImpl;
import com.bot.ws.service.impl.SendMessageServiceImpl;
import com.bot.ws.service.impl.SmartHomeControlServiceImpl;
import com.bot.ws.service.impl.SpotifyServiceImpl;
import com.bot.ws.service.impl.TextResponseClientServiceImpl;
import com.bot.ws.service.impl.VoiceResponseClientServiceImpl;
import com.twilio.Twilio;

@Configuration
@ImportResource("classpath:context/u-bot-client-context.xml")
public class UbotClientConfiguration {

	@Autowired
	private AppProperties properties;

	@Bean
	public ReceiveMessageService receiveMessageService() {
		return new ReceiveMessageServiceImpl();
	}

	@Bean
	public SendMessageService sendMessageService() {
		Twilio.init(properties.getAccountSid(), properties.getAuthToken());
		return new SendMessageServiceImpl();
	}

	@Bean
	public TextResponseClientService textResponseClientService() {
		return new TextResponseClientServiceImpl();
	}

	@Bean
	public VoiceResponseClientService voiceService() {
		return new VoiceResponseClientServiceImpl();
	}

	// Raspberry pi GPIO
	@Bean
	public PinControlService pinController() {
		return new PinControlServiceImpl();
	}

	@Bean
	public SpotifyService spotifyService() {
		return new SpotifyServiceImpl();
	}

	@Bean
	public MusicIntents musicIntents() {
		return new MusicIntents();
	}

	@Bean
	public IgnoreIntents ignoreIntents() {
		return new IgnoreIntents();
	}
	
	@Bean
	public SmartHomeControlService smartHomeControl(){
		return new SmartHomeControlServiceImpl();
	}

}
