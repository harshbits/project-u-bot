package com.bot.ws.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bot.ws.config.AppProperties;
import com.bot.ws.service.SendMessageService;

public class SendMessageServiceImpl implements SendMessageService {

	@Autowired
	private AppProperties properties;

//	private Queue<SenderData> allMessages = new LinkedList<SenderData>();

	@Override
	public void sendMessage() {

//		RestTemplate restTemplate = new RestTemplate();
//		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
//		map.add("senderId", data.getSender().getId());
//		map.add("recepientId", data.getReceiver().getId());
//		map.add("message", data.getMessage());
//		try {
//			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(
//					map);
//			ResponseEntity<SenderData> result = restTemplate.exchange(properties.getChatServerUrl(), HttpMethod.POST,
//					requestEntity, SenderData.class);
//			System.out.println(result.getStatusCode().name());
//		} catch (Exception e) {
//			// allMessages.add(data);
//			e.printStackTrace();
//
//		}

	}

}
