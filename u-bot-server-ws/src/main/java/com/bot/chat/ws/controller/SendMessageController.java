package com.bot.chat.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bot.chat.ws.service.SendMessageService;
//
//@Controller
//@RequestMapping("/send")
public class SendMessageController {

	@Autowired
	private SendMessageService sendService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> upload() {

		try {

			System.out.println("Hello World");

			sendService.sendMessage("hello");
			return new ResponseEntity<>("Hello World", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
