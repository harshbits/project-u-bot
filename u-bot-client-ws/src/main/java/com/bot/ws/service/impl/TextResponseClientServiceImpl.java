package com.bot.ws.service.impl;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bot.ws.config.IntentConfiguration.IgnoreIntents;
import com.bot.ws.config.IntentConfiguration.MusicIntents;
import com.bot.ws.config.model.enums.MusicEnum;
import com.bot.ws.service.PinControlService;
import com.bot.ws.service.SmartHomeControlService;
import com.bot.ws.service.SpotifyService;
import com.bot.ws.service.TextResponseClientService;
import com.bot.ws.service.VoiceResponseClientService;
import com.google.gson.JsonArray;

import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

public class TextResponseClientServiceImpl implements TextResponseClientService {

	private static final Logger logger = LoggerFactory.getLogger(TextResponseClientServiceImpl.class);

	@Autowired
	private AIDataService dataService;

	@Autowired
	private VoiceResponseClientService voiceService;

	@Autowired
	private MusicIntents musicIntents;

	@Autowired
	private IgnoreIntents ignoreIntents;

	@Autowired
	private SpotifyService spotifyService;
	
//	@Autowired
//	private MopidyClientWebSocketHandler mopidyClient;
	
	@Autowired
	private PinControlService pinControlService;
	
	@Autowired
	private SmartHomeControlService smartHomeControlService;
	
	private long activatedAt = Long.MAX_VALUE;
	
	private static final int DURATION = 15000;
	
	private boolean isUniceActive = false;
	
	@Override
	public String getParsedResponse(String input) {
		String parsedResponse = "";
		try {
			
			if(input.startsWith("frmph")){
				
				if(input.equals("frmph:wish her bday")){
					
					parsedResponse = "hmmmmmmmmmmmmmmmm Hey Vaishaali, wish you many many happy returns of the day.";
					voiceService.playResponse(parsedResponse);
				}
				if(input.equals("frmph:wish her bday msg")){
					parsedResponse = "hmmmmmmmmmmmmmmmm Hey Vaishaali, Happy Birthday! I might be not perfect, but my love towards you is. You are my true friend. I will always with you,"
							+ " I will support you, I will  boost you up whenever you will down."
							+ " Let your all the dreams to be on fire and light your birthday candles with that. Have a gorgeous birthday!"
							+ "and yes,... chutiyaa, benchod.. sexy b'day!!";
					voiceService.playResponse(parsedResponse);
				}
				if(input.equals("frmph:her bday msg")){
					parsedResponse = "And lastly,,..!!! Every great dream begins with the dreamer. Always remember.. you have within you the strength.. the presence.. "
							+ "and the passion to reach for the stars to change the world....! you know what I mean.. hehe !";
					voiceService.playResponse(parsedResponse);
					
				}
				if(input.equals("frmph:intro her")){
					parsedResponse = "hmmmmmmmmmmmmmmmm and yeah, I am part of your birthday gift, and I know word chutiya.!! haha.";
					voiceService.playResponse(parsedResponse);
					
				}
				if(input.equals("frmph:light on 0")){
					pinControlService.off(0);
					logger.info("Light on 0");
				}
				
				if(input.equals("frmph:light on 2")){
					pinControlService.off(2);
					logger.info("Light on 2");
				}
				
				if(input.equals("frmph:light off 0")){
					pinControlService.on(0);
					logger.info("Light off 0");
				}
				
				if(input.equals("frmph:light off 2")){
					pinControlService.on(2);
					logger.info("Light off 2");
				}
				
				
			}else{
				
				activatedAt = System.currentTimeMillis();
				
				AIRequest request = new AIRequest(input.toLowerCase());
				
//				dataService.resetContexts();
				AIResponse response = dataService.request(request);

				if (response.getStatus().getCode() == 200) {
					String action = response.getResult().getAction().trim();
					logger.info("Input Action:- "+ action);
					
//					if(mopidyClient.getSpotifyPlayer().getPlaybackState() != null){
//						if(mopidyClient.getSpotifyPlayer().getPlaybackState() == PlaybackState.PLAYING){
//						
//							logger.info("Song is playing");
//							
//						}else{
//							logger.info("Song is not playing");
//						}
//					}else{
//						logger.info("Song is not playing");
//					}
					
//					
					if (action.trim().startsWith(ignoreIntents.getIgnoreIntents())) {
						parsedResponse = response.getResult().getFulfillment().getSpeech();
						if(parsedResponse.trim().length() > 0){
							logger.info(parsedResponse);
							voiceService.playResponse(parsedResponse);
						}else{
							voiceService.playResponse("Sorry. I din't get it.");
						}
					}
					else if(musicIntents.getMusicIntents().containsKey(action)){
						MusicEnum command = MusicEnum.valueOf(musicIntents.getMusicIntents().get(action));
						String song = "";
//						String playlist = "";
//						String genre = "";
						String artist[] = {};
						try{
							
							if(response.getResult().getParameters().get("song") != null){
								song = response.getResult().getParameters().get("song").getAsString();
							}
							else if(response.getResult().getParameters().get("any") !=null){
								song = response.getResult().getParameters().get("any").getAsString(); 
							}
//							else if(response.getResult().getParameters().get("playlist") !=null){
//								playlist = response.getResult().getParameters().get("playlist").getAsString();
//							}
//							
//							else if(response.getResult().getParameters().get("genre") !=null){
//								genre = response.getResult().getParameters().get("genre").getAsString();
//							}
							else if(response.getResult().getParameters().get("artist") !=null){
								artist = jsonArrayToStringArray(response.getResult().getParameters().get("artist").getAsJsonArray());
							}
							
						}catch(Exception e){
							logger.error("Exception : "+ e.getMessage());
						}
						spotifyService.executeMethod(command, song, null); 
					}
					
					else if(action.trim().startsWith("smarthome")){
						smartHomeControlService.controlDevice(response);
					}
						
					else{
						parsedResponse = response.getResult().getFulfillment().getSpeech();
						if(parsedResponse.trim().length() > 0){
							logger.info(parsedResponse);
							voiceService.playResponse(parsedResponse);
						}else{
							voiceService.playResponse("Sorry, I didn't get it.");
						}
					}

				} else {
					voiceService.playResponse("Sorry. I din't get it.");
					logger.error(response.getStatus().getErrorDetails());
				}
			}
			
		} catch (Exception e) {
			voiceService.playResponse("Sorry. I din't get it.");
			logger.error("{}", e);
		}
		return parsedResponse;
	}
	
	private String[] jsonArrayToStringArray(JsonArray jsonArray) throws JSONException {
		int arraySize = jsonArray.size();
		String[] stringArray = new String[arraySize];

		for (int i = 0; i < arraySize; i++) {
			stringArray[i] = jsonArray.get(0).getAsString();
		}
		return stringArray;
	}

	public boolean isUniceActive() {
		return isUniceActive;
	}

	public void setUniceActive(boolean isUniceActive) {
		this.isUniceActive = isUniceActive;
	}
	
	private boolean isContextActive() {
		long activeFor = System.currentTimeMillis() - activatedAt;
		return activeFor >= 0 && activeFor <= DURATION;
	}
	
	
}
