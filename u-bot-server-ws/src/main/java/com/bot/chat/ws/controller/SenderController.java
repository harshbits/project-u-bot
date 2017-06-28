package com.bot.chat.ws.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bot.chat.ws.beans.ErrorObject;
import com.bot.chat.ws.beans.SenderData;
import com.bot.chat.ws.beans.User;
import com.bot.chat.ws.service.SendDataService;

@Controller
@RequestMapping("/send")
public class SenderController {

	@Autowired
	private SendDataService sendDataService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> upload(@RequestParam(required = true) String senderId,
			@RequestParam(required = true) String recepientId, @RequestParam(required = true) String message) {

		SenderData data = new SenderData();
		try {

			// User sender = redisTemplate.opsForValue().get(senderId);
			//
			// User receiver = redisTemplate.opsForValue().get(recepientId);
			User sender = new User();
			sender.setUserQueue(senderId);
			sender.setId(senderId);
			sender.setName(senderId.split("\\-",2)[0].trim().toUpperCase());

			User receiver = new User();
			receiver.setUserQueue(recepientId);
			receiver.setId(recepientId);
			receiver.setName(recepientId.split("\\-",2)[0].trim().toUpperCase());

			data.setSender(sender);
			data.setReceiver(receiver);
			data.setMessage(message);

			sendDataService.sendDataMessage(data);

			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			List<ErrorObject> errors = new ArrayList<ErrorObject>();
			errors.add(new ErrorObject(HttpStatus.BAD_REQUEST.toString(), "Country or companyCode must be present."));
			return new ResponseEntity<List<ErrorObject>>(errors, HttpStatus.BAD_REQUEST);
		}

	}

}
