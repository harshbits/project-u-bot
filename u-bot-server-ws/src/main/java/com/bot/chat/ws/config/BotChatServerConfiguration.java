package com.bot.chat.ws.config;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.github.fedy2.weather.YahooWeatherService;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;

@Configuration
public class BotChatServerConfiguration {

	@Autowired
	private AppProperties properties;

	private static final String OAUTH2_PATH = "u-bot-client-auth.json";

	@Bean
	public GoogleCredential credential() {
		GoogleCredential creds = null;
		try {
			creds = GoogleCredential.fromStream(new FileInputStream(new ClassPathResource(OAUTH2_PATH).getFile()))
					.createScoped(properties.getoAuth2Scopes());
		} catch (Exception e) {
		}
		return creds;
	}

	@Bean
	public Customsearch customSearch() throws IOException {
		GoogleCredential credential = credential();
		credential.refreshToken();
		HttpTransport httpTransport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();
		Customsearch search = new Customsearch.Builder(httpTransport, jsonFactory, null)
				.setApplicationName("u-bot-search").setHttpRequestInitializer(credential).build();
		return search;
	}

	@Bean
	public YahooWeatherService yahooWeatherService() throws JAXBException {
		return new YahooWeatherService();
	}

}
