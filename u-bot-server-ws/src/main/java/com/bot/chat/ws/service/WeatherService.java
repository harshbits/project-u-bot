package com.bot.chat.ws.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.time.DateUtils;
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

		// Process the request and get requested date
		Date date = determineDate(request);

		// Process Temperature/weather condition related request
		if (action.equals(IntentConstant.WEATHER_INTENT) || action.equals(IntentConstant.WEATHER_TEMPERATURE_INTENT)) {
			if (location != null && !location.isEmpty()) {
				DegreeUnit degreeUnit = getDegreeUnit(request);
				return getTemperatureResponse(location, date, degreeUnit);
			}
		}
		// Process Activity related intent
		else if (action.equals(IntentConstant.WEATHER_ACTIVITY_INTENT)) {
			String activity = getActivity(request);
			if (activity != null && !activity.isEmpty()) {
				return getActivityResponse(request, date, activity);
			}
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
	private String getTemperatureResponse(String location, Date date, DegreeUnit unit) {
		String output = "";
		// Yahoo weather service has this format as date
//		SimpleDateFormat targetSdf = new SimpleDateFormat("dd MMM yyyy");
		try {
			yahooWeatherService.getForecastForLocation(location, unit).all().get(0).getItem().getForecasts();
			Channel channel = yahooWeatherService.getForecastForLocation(location, unit).all().get(0);

			if (channel != null) {
				String city = channel.getLocation().getCity();
				if (DateUtils.isSameDay(date, Calendar.getInstance().getTime())) {
					String condition = channel.getItem().getCondition().getText();
					int temperature = channel.getItem().getCondition().getTemp();
					String unitValue = channel.getUnits().getTemperature().name();
					output = "Today in " + city + " it is " + condition + ", the temperature is " + temperature
							+ " degree " + unitValue.toLowerCase();
				} else {
					final Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, 1);
					// If tomorrow
					if (DateUtils.isSameDay(date, cal.getTime())) {
						String condition = channel.getItem().getForecasts().get(1).getText();
						int highTemp = channel.getItem().getForecasts().get(1).getHigh();
						int lowTemp = channel.getItem().getForecasts().get(1).getLow();
						String unitValue = channel.getUnits().getTemperature().name();
						output = "Tomorrow in " + city + " it will be " + condition + ", the high temperature will be "
								+ highTemp + " degree " + unitValue.toLowerCase() + "and  high temperature will be "
								+ lowTemp + " degree " + unitValue.toLowerCase();
					}
				}
			}
		} catch (JAXBException | IOException e) {
			log.error("Get temperature error {}", e.getMessage());
		}
		return output;
	}

	/**
	 * Get response for weather.activity action, such as running, going out
	 * skiing, etc.
	 * 
	 * @param request
	 * @return
	 */
	private String getActivityResponse(WebhookRequest request, Date date, String activity) {
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
		// Default degree unit to CELSIUS
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
	 * Get activity from the weather activity intent request
	 * 
	 * @param request
	 * @return
	 */
	private String getActivity(WebhookRequest request) {
		String activity = "";
		try {
			activity = (String) request.getResult().getContexts().get(0).getParameters().get("activity");
		} catch (Exception e) {
			log.error("Get Activity error {}", e.getMessage());
		}
		return activity;
	}

	/**
	 * This method determines date string for the request. It first checks
	 * whether response JSON object from api.ai is array or not, if not than it
	 * parses string as date. In any failure it will return today's date as
	 * response
	 * 
	 * @param request
	 * @return
	 */
	private Date determineDate(WebhookRequest request) {
		Gson gson = new Gson();
		Date date = new Date();
		SimpleDateFormat inputSdf = new SimpleDateFormat("yyyy-mm-dd");

		try {
			Type type = new TypeToken<List<String>>() {
			}.getType();
			String dateString = (String) request.getResult().getContexts().get(0).getParameters().get("date-time");
			if (dateString != null && !dateString.isEmpty()) {
				try {
					List<String> dateArray = gson.fromJson(dateString, type);
					if (dateArray != null && dateArray.size() > 0) {
						dateString = dateArray.get(0);
						date = inputSdf.parse(dateString);
					}
				} catch (Exception e) {
					log.error("Date string is not of type array: {}", e.getMessage());
					date = inputSdf.parse(dateString);
				}
			} else {
				date = new Date();
			}
		} catch (Exception e) {
			log.error("Get Date string error {}", e.getMessage());
			date = new Date();
		}
		return date;
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
				// Default city - Configured
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
