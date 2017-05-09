package com.bot.ws.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfiguration {

	@Autowired
	private AppProperties properties;
	
	@Autowired
    private ConnectionFactory connectionFactory;
	
	@Bean
	public AmqpAdmin rabbitAdmin() {
		return new RabbitAdmin(connectionFactory);
	}
		
	@Bean
	public DirectExchange chatExchange() {
	    return new DirectExchange(properties.getuBotExchangeName(), true, false);	
	}
	
	@Bean 
	Queue chatQueue() {
		return new Queue(properties.getuBotQueue(), true);
	}

	@Bean
	Binding UbotQueueBinding() {
		return BindingBuilder.bind(chatQueue()).to(chatExchange()).with(properties.getuBotQueue());
	}
	
}
