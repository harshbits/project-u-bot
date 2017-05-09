package com.bot.ws.service;

public interface VoiceResponseClientService {
	
	public void playResponse(String textInput);
	
	public boolean isPlayBackRunning();
}
