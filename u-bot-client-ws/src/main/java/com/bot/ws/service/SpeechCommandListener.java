package com.bot.ws.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bot.ws.config.AppProperties;
import com.bot.ws.service.impl.MopidyClientWebSocketHandler;
import com.google.auth.oauth2.GoogleCredentials;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class SpeechCommandListener {

	@Autowired
	private AppProperties properties;

	@Autowired
	private TextResponseClientService textService;
	
	@Autowired
	private VoiceResponseClientService voiceClient;
	
//	@Autowired
//	private MopidyClientWebSocketHandler mopidyClient; 
	
	@Autowired
	private GoogleCredentials creds;

	private static final Logger log = LoggerFactory.getLogger(SpeechCommandListener.class);

	private StreamingRecognizeClient client;
	
	@Scheduled(fixedRate = 55000)
	public void SpeechCommandListenTask() {
		ManagedChannel channel = null;
		try {
			channel = ManagedChannelBuilder.forAddress(properties.getSpeechHost(), properties.getSpeechPort()).build();
			client = new StreamingRecognizeClient();
			client.setChannel(channel);
			client.setCreds(creds);
			client.setBytesPerSample(properties.getBytesPerSample());
			client.setRecorderSampleRate(properties.getRecorderSampleRate());
			client.setTextService(textService);
			client.setVoiceClient(voiceClient);
//			client.setMopidyClient(mopidyClient);
			client.init();
			client.recognize();
			Thread.sleep(50000);
			client.shutdown(channel);
			System.gc();
		} catch (Throwable t) {
			log.error("SpeechCommandListenTask Exception: " + t.getMessage());
			if (client == null) {
				SpeechCommandListenTask();
			} else {
				if (client.isTerminated(channel)) {
					SpeechCommandListenTask();
				}
			}
		}
	}
}
