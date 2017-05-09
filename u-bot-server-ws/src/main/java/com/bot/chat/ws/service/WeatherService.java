package com.bot.chat.ws.service;

import com.bot.chat.ws.beans.WebhookRequest;
import com.github.fedy2.weather.data.unit.DegreeUnit;

public interface WeatherService {
	
	public String handleRequest(WebhookRequest request);

	public String getTemperature(String location, DegreeUnit unit);
	
}
