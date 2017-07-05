package com.bot.chat.ws.service;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.chat.ws.beans.WebhookRequest;
import com.bot.chat.ws.constants.IntentConstant;
import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.unit.DegreeUnit;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

/**
 * This service handles all weather/outdoor activities related webhook requests
 * 
 * @author harshbhavsar
 *
 */
@Service
@Slf4j
public class WeatherService {

	@Autowired
	private YahooWeatherService yahooWeatherService;

	/**
	 * This method handles Webhook request (which comes from api.ai response)
	 * and process it based on intent.
	 * 
	 * @param request
	 * @return
	 */
	public String handleRequest(WebhookRequest request, String action) {

		// Process the request and get city/location
		String location = processRequestToGetCity(request);

		// Process Temperature/weather condition related request
		if (action.equals(IntentConstant.WEATHER_INTENT) || action.equals(IntentConstant.WEATHER_TEMPERATURE_INTENT)) {
			if (location != null && !location.isEmpty()) {
				DegreeUnit degreeUnit = getDegreeUnit(request);
				return getTemperatureResponse(location, degreeUnit);
			}
		}
		// Process Activity related intent
		else if (action.equals(IntentConstant.WEATHER_ACTIVITY_INTENT)) {

		}

		return "";
	}
	
	/**
	 * Get temperature for the particular location in requested unit (C or F)
	 * format
	 * 
	 * @param location
	 * @param unit
	 * @return
	 */
	private String getTemperatureResponse(String location, DegreeUnit unit) {
		String output = "";
		try {
			yahooWeatherService.getForecastForLocation(location, unit).all().get(0).getItem().getForecasts();
			Channel channel = yahooWeatherService.getForecastForLocation(location, unit).all().get(0);
			if (channel != null) {
				String city = channel.getLocation().getCity();
				String condition = channel.getItem().getCondition().getText();
				int temperature = channel.getItem().getCondition().getTemp();
				String unitValue = channel.getUnits().getTemperature().name();
				output = "Today in " + city + " it is " + condition + ", the temperature is " + temperature + " degree "
						+ unitValue.toLowerCase();
			}
		} catch (JAXBException | IOException e) {
			log.error("Get temperature error {}", e.getMessage());
		}
		return output;
	}

	
	private String getActivityResponse(WebhookRequest request){
		String output = "";
		
		return output;
	}
	
	
	
	/**
	 * Get degree unit as per the webhook request
	 * 
	 * @param request
	 * @return
	 */
	private DegreeUnit getDegreeUnit(WebhookRequest request) {
		//Default degree unit to CELSIUS
		DegreeUnit unit = DegreeUnit.CELSIUS;
		try {
			String unitCode = request.getResult().getParameters().get("unit") != null
					? request.getResult().getParameters().get("unit").toString() : "";
			if (!unitCode.isEmpty() && unitCode.equals("F")) {
				unit = DegreeUnit.FAHRENHEIT;
			}
		} catch (Exception e) {
			log.error("Get Temperature unit error {}", e.getMessage());
		}
		return unit;
	}
	
	/**
	 * This method processes webhook request and returns appropriate city to
	 * process the request
	 * 
	 * @param request
	 * @return
	 */
	private String processRequestToGetCity(WebhookRequest request) {
		String city = "";
		Gson gson = new Gson();
		try {
			Type type = new TypeToken<LinkedTreeMap<String, String>>() {
			}.getType();
			LinkedTreeMap<String, String> addressMap = gson
					.fromJson(request.getResult().getParameters().get("address").toString(), type);
			if (addressMap != null) {
				if (addressMap.size() > 0) {
					city = addressMap.get("city");
				}
				//Default city - Configured
				if (city == null) {
					city = (String) request.getResult().getContexts().get(0).getParameters().get("geo-city");
				}
			}
		} catch (Exception e) {
			log.error("Handle webhook request error {}", e);
		}

		return city;
	}
}
