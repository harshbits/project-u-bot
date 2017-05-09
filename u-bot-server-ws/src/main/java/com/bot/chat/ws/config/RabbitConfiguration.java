package com.bot.chat.ws.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

	@Autowired
	private AppProperties properties;
	
	@Bean
	public DirectExchange relistProcessExchange() {
	    return new DirectExchange(properties.getChatExchangeName(), true, false);	
	}

}
