package com.bot.ws.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twilio.twiml.Body;
import com.twilio.twiml.Message;
import com.twilio.twiml.MessagingResponse;

@Controller
@RequestMapping("/sms")
public class ReceiveMessageController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReceiveMessageController.class);

	@RequestMapping(method = { RequestMethod.GET })
	public void service(HttpServletRequest request, HttpServletResponse response) {

		HashMap<String, String> callers = new HashMap<>();
		callers.put("+14694070782", "Harsh Bhavsar");
		callers.put("+12672403339", "vaish\"U\"");
		callers.put("+15109355258", "Mittal Patel");

		String fromNumber = request.getParameter("From");
		String data = request.getParameter("Body");
		String knownCaller = callers.get(fromNumber);
		String message;

		if (knownCaller == null) {
			// Use a generic message
			message = "Monkey, thanks for the message!";
		} else {

			logger.info("Known Caller: " + knownCaller);
			if (knownCaller.equals("vaish\"U\"")) {
				message = knownCaller + ", thanks for the message! - Kam gussa and Jyada Pyaar";
			} else {
				message = knownCaller + ", thanks for the message! - " + data;
			}
		}

		// Create a TwiML response and add our friendly message.
		MessagingResponse twiml = new MessagingResponse.Builder()
				.message(new Message.Builder().body(new Body(message)).build()).build();

		response.setContentType("application/xml");
		try {
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