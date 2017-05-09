package com.bot.ws.config;

import java.util.ArrayList;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.properties")
public class AppProperties {

	private String uBotExchangeName;

	private String uBotQueue;

	private String speechHost;

	private int speechPort;

	private int recorderSampleRate;

	private int bytesPerSample;

	private ArrayList<String> oAuth2Scopes;

	private ArrayList<String> uBotCalls;

	private String apiAiAuthToken;

	private String awsAccessKey;

	private String awsSecretKey;

	private String awsPollyVoiceId;

	private String accountSid;

	private String authToken;

	private String spotifyClientId;

	private String spotifyClientSecret;

	private String mopidyWebSocketUrl;

	public String getuBotExchangeName() {
		return uBotExchangeName;
	}

	public void setuBotExchangeName(String uBotExchangeName) {
		this.uBotExchangeName = uBotExchangeName;
	}

	public String getuBotQueue() {
		return uBotQueue;
	}

	public void setuBotQueue(String uBotQueue) {
		this.uBotQueue = uBotQueue;
	}

	public String getSpeechHost() {
		return speechHost;
	}

	public void setSpeechHost(String speechHost) {
		this.speechHost = speechHost;
	}

	public int getSpeechPort() {
		return speechPort;
	}

	public void setSpeechPort(int speechPort) {
		this.speechPort = speechPort;
	}

	public ArrayList<String> getoAuth2Scopes() {
		return oAuth2Scopes;
	}

	public void setoAuth2Scopes(ArrayList<String> oAuth2Scopes) {
		this.oAuth2Scopes = oAuth2Scopes;
	}

	public int getRecorderSampleRate() {
		return recorderSampleRate;
	}

	public void setRecorderSampleRate(int recorderSampleRate) {
		this.recorderSampleRate = recorderSampleRate;
	}

	public int getBytesPerSample() {
		return bytesPerSample;
	}

	public void setBytesPerSample(int bytesPerSample) {
		this.bytesPerSample = bytesPerSample;
	}

	public ArrayList<String> getuBotCalls() {
		return uBotCalls;
	}

	public void setuBotCalls(ArrayList<String> uBotCalls) {
		this.uBotCalls = uBotCalls;
	}

	public String getApiAiAuthToken() {
		return apiAiAuthToken;
	}

	public void setApiAiAuthToken(String apiAiAuthToken) {
		this.apiAiAuthToken = apiAiAuthToken;
	}

	public String getAwsAccessKey() {
		return awsAccessKey;
	}

	public void setAwsAccessKey(String awsAccessKey) {
		this.awsAccessKey = awsAccessKey;
	}

	public String getAwsSecretKey() {
		return awsSecretKey;
	}

	public void setAwsSecretKey(String awsSecretKey) {
		this.awsSecretKey = awsSecretKey;
	}

	public String getAwsPollyVoiceId() {
		return awsPollyVoiceId;
	}

	public void setAwsPollyVoiceId(String awsPollyVoiceId) {
		this.awsPollyVoiceId = awsPollyVoiceId;
	}

	public String getAccountSid() {
		return accountSid;
	}

	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getSpotifyClientId() {
		return spotifyClientId;
	}

	public void setSpotifyClientId(String spotifyClientId) {
		this.spotifyClientId = spotifyClientId;
	}

	public String getSpotifyClientSecret() {
		return spotifyClientSecret;
	}

	public void setSpotifyClientSecret(String spotifyClientSecret) {
		this.spotifyClientSecret = spotifyClientSecret;
	}

	public String getMopidyWebSocketUrl() {
		return mopidyWebSocketUrl;
	}

	public void setMopidyWebSocketUrl(String mopidyWebSocketUrl) {
		this.mopidyWebSocketUrl = mopidyWebSocketUrl;
	}

}
