package com.bot.chat.ws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bot.chat.ws.beans.WebhookRequest;
import com.bot.chat.ws.beans.WebhookResponse;
import com.bot.chat.ws.service.GoogleSearchService;
import com.bot.chat.ws.service.WeatherService;

@Controller
@RequestMapping("/webhook")
public class WebhookController {
	
	private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);
	
	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	private GoogleSearchService googleSearchService;
	

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody WebhookResponse webhook(@RequestBody WebhookRequest payload) throws Exception {
		
		WebhookResponse response = new WebhookResponse();
		try {
			assignTaskOnIntent(payload, response);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return response;
    }
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> hadnleException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	protected void assignTaskOnIntent(WebhookRequest request, WebhookResponse response){
		
		String action = request.getResult().getAction().toString();
		
		logger.info("Action: "+ action);
		String responseString = "";
		switch(action){
			case "weather":
				responseString = weatherService.handleRequest(request);
				break;
			case "web":
				responseString = googleSearchService.handleRequest(request);
				break;
		}
		logger.info("Speech Text {}", responseString);
		response.setSpeech(responseString);
		response.setDisplayText("Action triggered: "+ action);
	}
	
}
