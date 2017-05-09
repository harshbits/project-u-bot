package com.bot.ws;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.bot.ws.service.PinControlService;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class UBotClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(UBotClientApplication.class, args);
	}

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private PinControlService pinControlService;

	@PostConstruct
	public void init() {
		try {
			// smart home
			String lightStatus = redisTemplate.opsForValue().get("smarthome.light");
			String lampStatus = redisTemplate.opsForValue().get("smarthome.lamp");

			if (lightStatus.equals("OFF")) {
				pinControlService.on(2);
			}
			else if (lightStatus.equals("ON")) {
				pinControlService.off(2);
			}

			if (lampStatus.equals("ON")) {
				pinControlService.off(0);
			}

			else if (lampStatus.equals("OFF")) {
				pinControlService.on(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
