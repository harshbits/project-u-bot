package com.bot.ws.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.bot.ws.service.VoiceResponseClientService;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class VoiceResponseClientServiceImpl implements VoiceResponseClientService {

	private final static Logger logger = LoggerFactory.getLogger(VoiceResponseClientServiceImpl.class);

	@Autowired
	private AmazonPolly amazonPollyClient;

	private boolean isPlaybackRunning = false;
	
	@Override
	public void playResponse(String textInput) {
		InputStream speechStream;
		try {
			speechStream = synthesize(textInput, OutputFormat.Mp3);

			// create an MP3 player
			AdvancedPlayer player = new AdvancedPlayer(speechStream,
					javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());

			player.setPlayBackListener(new PlaybackListener() {
				@Override
				public void playbackStarted(PlaybackEvent evt) {
					isPlaybackRunning = true;
					logger.info("Playback started");
				}
				@Override
				public void playbackFinished(PlaybackEvent evt) {
					logger.info("Playback finished");
					isPlaybackRunning = false;
				}
			});
			player.play();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private InputStream synthesize(String text, OutputFormat format) throws IOException {
		SynthesizeSpeechRequest synthReq = new SynthesizeSpeechRequest().withText(text).withVoiceId("Joanna")
				.withOutputFormat(format);
		SynthesizeSpeechResult synthRes = amazonPollyClient.synthesizeSpeech(synthReq);

		return synthRes.getAudioStream();
	}

	@Override
	public boolean isPlayBackRunning() {
		return isPlaybackRunning;
	}
	
}
