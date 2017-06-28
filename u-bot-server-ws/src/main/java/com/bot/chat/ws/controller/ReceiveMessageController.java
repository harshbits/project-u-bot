package com.bot.chat.ws.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bot.chat.ws.config.AppProperties;
import com.twilio.twiml.Body;
import com.twilio.twiml.Message;
import com.twilio.twiml.MessagingResponse;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/sms")
@Slf4j
public class ReceiveMessageController {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private AppProperties properties;

	@RequestMapping(method = { RequestMethod.GET })
	public void service(HttpServletRequest request, HttpServletResponse response) {

		HashMap<String, String> callers = new HashMap<>();
		callers.put("+14694070782", "Harsh Bhavsar");
		callers.put("+12672403339", "vaish\"U\"");
		callers.put("+15109355258", "Mittal Patel");

		String fromNumber = request.getParameter("From");
		String data = request.getParameter("Body");
		String knownCaller = callers.get(fromNumber);
		
//		rabbitTemplate.convertSendAndReceive(message)
//		rabbitTemplate.setexpira
//		AMQP.BasicProperties properties = new AMQP.BasicProperties();
//		properties.set
		
		String rabbitResponse = (String) rabbitTemplate.convertSendAndReceive(properties.getChatExchangeName(), "ubot-queue", data);
		
		log.info("Rabbit Response: {}", rabbitResponse);

		if(rabbitResponse == null){
			rabbitResponse = "Sorry what was that again?";
		}
		
		try {
			// Create a TwiML response and add our friendly message.
			MessagingResponse twiml = new MessagingResponse.Builder()
					.message(new Message.Builder().body(new Body(rabbitResponse)).build()).build();

			response.setContentType("application/xml");

			response.getWriter().print(twiml.toXml());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> hadnleException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
