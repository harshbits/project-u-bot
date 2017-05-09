package com.bot.chat.ws.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bot.chat.ws.beans.SenderData;
import com.bot.chat.ws.config.AppProperties;
import com.bot.chat.ws.service.SendDataService;

public class SendDataServiceImpl implements SendDataService{

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private AppProperties properties;
	
	@Override
	public void sendDataMessage(SenderData data) {
//		Message message = MessageBuilder.withBody(data.getMessage().getBytes()).setMessageId(data.getMessage())
//				.setContentType("text/plain").build();
//		rabbitTemplate.send(properties.getChatExchangeName(), data.getReceiver().getId(), message);
		rabbitTemplate.convertAndSend(properties.getChatExchangeName(), data.getReceiver().getId(), data);
	}
}
