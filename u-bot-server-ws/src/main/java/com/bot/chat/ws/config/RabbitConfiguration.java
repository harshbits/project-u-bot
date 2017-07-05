package com.bot.chat.ws.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bot.chat.ws.config.AppProperties.Properties;

/**
 * Rabbit configuration class
 * 
 * @author harshbhavsar
 *
 */
@Configuration
public class RabbitConfiguration {

	@Autowired
	private Properties properties;
	
	@Bean
	public DirectExchange relistProcessExchange() {
	    return new DirectExchange(properties.getChatExchangeName(), true, false);	
	}

}
