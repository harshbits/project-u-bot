package com.bot.ws.config;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.google.auth.oauth2.GoogleCredentials;
import com.wrapper.spotify.Api;

import ai.api.AIConfiguration;
import ai.api.AIDataService;

@Configuration
public class SoundTextAPIConfiguration {

	private static final String OAUTH2_PATH = "classpath:u-bot-client-auth-2.json";

	@Autowired
	private AppProperties appProperties;

	@Bean
	public GoogleCredentials credentials() {
		GoogleCredentials creds = null;
		try {
			
		    Resource resource = new FileSystemResourceLoader().getResource(OAUTH2_PATH);
            InputStream dbAsStream = resource.getInputStream(); 
//			creds = GoogleCredentials.fromStream(new FileInputStream(new ClassPathResource(OAUTH2_PATH).getFile()));
            creds = GoogleCredentials.fromStream(dbAsStream);
			creds = creds.createScoped(appProperties.getoAuth2Scopes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creds;
	}

	@Bean
	public AIDataService dataService() {
		AIConfiguration configuration = new AIConfiguration(appProperties.getApiAiAuthToken());
		AIDataService dataService = new AIDataService(configuration);
		return dataService;
	}

	@Bean
	public AmazonPolly amazonPollyClient() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(appProperties.getAwsAccessKey(),
				appProperties.getAwsSecretKey());
		return AmazonPollyClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.withRegion(Regions.US_WEST_2).build();
	}
	
	@Bean
	public Api spotifyApi() {
		return Api
				.builder()
				.clientId(appProperties.getSpotifyClientId())
				.clientSecret(appProperties.getSpotifyClientSecret())
				.build();
	}
	
}
