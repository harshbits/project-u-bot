package com.bot.chat.ws.service.impl;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;

import com.bot.chat.ws.beans.WebhookRequest;
import com.bot.chat.ws.service.WeatherService;
import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.unit.DegreeUnit;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private  YahooWeatherService yahooWeatherService;

	@Override
	public String handleRequest(WebhookRequest request) {

		String city = "";
		String location = (String) request.getResult().getContexts().get(0).getParameters().get("geo-city");
		
		try{
			Gson gson = new Gson();
			Type type = new TypeToken<LinkedTreeMap<String, String>>(){}.getType();
			LinkedTreeMap<String, String>  addressMap = gson.fromJson(request.getResult().getParameters().get("address").toString(),type);
			if(addressMap!=null){
				if(addressMap.size() >0){
					city = addressMap.get("city");
				}
				if(city != null && city != ""){
					location = city;
				}
			}
		}catch(Exception e){
			log.error("{}", e);
		}
	
		return getTemperature(location, DegreeUnit.CELSIUS);
		
	}
	
	@Override
	public String getTemperature(String location, DegreeUnit unit) {

		String output = "";
		try {
			
			yahooWeatherService.getForecastForLocation(location, unit).all().get(0).getItem().getForecasts();
			Channel channel= yahooWeatherService.getForecastForLocation(location, unit).all().get(0);
			String city = channel.getLocation().getCity();
			
			String condition = channel.getItem().getCondition().getText();
			int temperature = channel.getItem().getCondition().getTemp();
			String unitValue = channel.getUnits().getTemperature().name();
			output = "Today in " + city + " it is " + condition + ", the temperature is " + temperature + " degree "
					+ unitValue.toLowerCase();
			
		} catch (JAXBException | IOException e) {
			log.error("Error ", e);
		}
		
		return output;
	}

}
