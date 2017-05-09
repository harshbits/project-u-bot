package com.bot.ws.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.bot.ws.service.PinControlService;
import com.bot.ws.service.SmartHomeControlService;

import ai.api.model.AIResponse;

public class SmartHomeControlServiceImpl implements SmartHomeControlService{
	
	private static final Logger logger = LoggerFactory.getLogger(SmartHomeControlServiceImpl.class);

	@Autowired
	private PinControlService pinControlService;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Override
	public void controlDevice(AIResponse response) {
		
		logger.info("Smart Home Response: "+ response.getResult().getAction());
		
		if(response.getResult().getAction().equals("smarthome.device.switch.on")){
			logger.info("Redis status: "+ redisTemplate.opsForValue().get("smarthome.lamp"));
			pinControlService.off(0);
			redisTemplate.opsForValue().set("smarthome.lamp", "ON");
			logger.info("Lamp ON");
		}
		else if(response.getResult().getAction().equals("smarthome.device.switch.off")){
			logger.info("Redis status: "+ redisTemplate.opsForValue().get("smarthome.lamp"));
			pinControlService.on(0);
			redisTemplate.opsForValue().set("smarthome.lamp", "OFF");
			logger.info("Lamp OFF");
		}
		
		else if(response.getResult().getAction().equals("smarthome.lights.switch.on")){
			logger.info("Redis status: "+ redisTemplate.opsForValue().get("smarthome.light"));
			pinControlService.off(2);
			redisTemplate.opsForValue().set("smarthome.light", "ON");
			logger.info("Light ON");
		}
		
		else if(response.getResult().getAction().equals("smarthome.lights.switch.off")){
			logger.info("Redis status: "+ redisTemplate.opsForValue().get("smarthome.light"));
			pinControlService.on(2);
			redisTemplate.opsForValue().set("smarthome.light", "OFF");
			logger.info("Light OFF");
		}
	}

}
