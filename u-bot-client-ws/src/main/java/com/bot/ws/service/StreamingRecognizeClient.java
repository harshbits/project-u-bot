package com.bot.ws.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.bot.ws.service.impl.MopidyClientWebSocketHandler;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1beta1.RecognitionConfig;
import com.google.cloud.speech.v1beta1.SpeechGrpc;
import com.google.cloud.speech.v1beta1.StreamingRecognitionConfig;
import com.google.cloud.speech.v1beta1.StreamingRecognitionResult;
import com.google.cloud.speech.v1beta1.StreamingRecognizeRequest;
import com.google.cloud.speech.v1beta1.StreamingRecognizeResponse;
import com.google.protobuf.ByteString;

import io.grpc.ManagedChannel;
import io.grpc.auth.MoreCallCredentials;
import io.grpc.stub.StreamObserver;

@Component
public class StreamingRecognizeClient {
	private static final Logger logger = Logger.getLogger(StreamingRecognizeClient.class);
	
	private TextResponseClientService textService;
	
	private GoogleCredentials creds;

	private ManagedChannel channel;

	private SpeechGrpc.SpeechStub speechClient;

	private int bytesPerSample;

	private int bytesPerBuffer;

	private int recorderSampleRate;

	private StreamObserver<StreamingRecognizeResponse> responseObserver;

	private StreamObserver<StreamingRecognizeRequest> requestObserver;

	private TargetDataLine recorder;
	
	private VoiceResponseClientService voiceClient;
	
//	private MopidyClientWebSocketHandler mopidyClient;
	
	private static final int DURATION = 15000;
	
	private long activatedAt = Long.MAX_VALUE;

//	private static final String[] EUNICE_CALL= {"hey eunice", "hey unit", "hey unix", "hey ernest", "hey you nice", "hyannis", "eunice"} ;
	
	private static final String EUNICE_PREFIX = "(hey eunice|hey unit|hey unix|hey ernest|hey you nice|hyannis|eunice)" ;
	
	public void init() throws IOException {
		
		this.bytesPerBuffer = recorderSampleRate * bytesPerSample / 10;
		
		this.speechClient = SpeechGrpc.newStub(channel).withCallCredentials(MoreCallCredentials.from(creds));
		
		this.responseObserver = new StreamObserver<StreamingRecognizeResponse>() {
			@Override
			public void onNext(StreamingRecognizeResponse response) {
				List<StreamingRecognitionResult> results = response.getResultsList();
				if (results.size() < 1) {
					return;
				}

				StreamingRecognitionResult result = results.get(0);
				String transcript = result.getAlternatives(0).getTranscript();
				if (result.getIsFinal()) {
					logger.info("out final transcript: -> " + transcript);
					try{
						if(textService !=null && transcript !=null){
							
							if(isUniceActive()){
								if(!voiceClient.isPlayBackRunning()){
									textService.getParsedResponse(transcript);	
								}
								activatedAt = System.currentTimeMillis();
							}

							else if (transcript.trim().toLowerCase().matches(EUNICE_PREFIX+".*")){
								transcript = transcript.replaceFirst(EUNICE_PREFIX, "").trim().toLowerCase();
								if(transcript.length()<=0){
									transcript = "hey";
								}
								textService.getParsedResponse(transcript);
								activatedAt = System.currentTimeMillis();
							}
							
						}
					}catch(Exception e){
						logger.error("{}", e);
					}
				}
			}

			@Override
			public void onError(Throwable error) {
				logger.error("recognize failed: "+ error.getMessage());
				try {
					shutdown(channel);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onCompleted() {
				logger.info("Recognize complete");
				try {
					shutdown(channel);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		requestObserver = speechClient.streamingRecognize(responseObserver);
		
	}
	
	public void shutdown(ManagedChannel channel) throws InterruptedException {
		if (recorder != null) {
			recorder.stop();
			recorder.flush();
			recorder.close();
			recorder = null;
		}
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	/** Send streaming recognize requests to server. */
	public void recognize() throws InterruptedException, IOException {

		try {
			RecognitionConfig config = RecognitionConfig.newBuilder()
					.setEncoding(RecognitionConfig.AudioEncoding.LINEAR16).setSampleRate(this.recorderSampleRate)
					.setLanguageCode("en-US").build();

			StreamingRecognitionConfig streamingConfig = StreamingRecognitionConfig.newBuilder().setConfig(config)
					.setInterimResults(true).setSingleUtterance(false).build();

			StreamingRecognizeRequest initial = StreamingRecognizeRequest.newBuilder()
					.setStreamingConfig(streamingConfig).build();

			requestObserver.onNext(initial);
			recorder = getAudioInputLine();
			recorder.start();
			byte[] buffer = new byte[bytesPerBuffer];
			int recordState;

			// loop through the audio samplings
			while ((recordState = recorder.read(buffer, 0, buffer.length)) > -1) {

				// skip if there is no data
				if (recordState < 0 )
					continue;

				// create a new recognition request
				if(!voiceClient.isPlayBackRunning()){
					StreamingRecognizeRequest request = StreamingRecognizeRequest.newBuilder()
							.setAudioContent(ByteString.copyFrom(buffer, 0, buffer.length)).build();

					requestObserver.onNext(request);
				}
			}

		} catch (RuntimeException e) {
			// Cancel RPC.
			logger.error("Run time error: " + e.getMessage());
			requestObserver.onError(e);
			throw e;
		} catch (Exception e) {
			logger.info("Global exception: " + e.getMessage());
		}
		// Mark the end of requests.
		requestObserver.onCompleted();
	}

	/**
	 * Return a Line to the audio input device.
	 */
	private TargetDataLine getAudioInputLine() {

		AudioFormat format = new AudioFormat(recorderSampleRate, bytesPerSample * 8, 1, true, false);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		if (!AudioSystem.isLineSupported(info)) {
			throw new RuntimeException(String.format("Device doesn't support LINEAR16 mono raw audio format at {%d}Hz",
					recorderSampleRate));
		}
		try {
			TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format, bytesPerBuffer * 5);
			return line;
		} catch (LineUnavailableException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isTerminated(ManagedChannel channel) {
		return channel.isTerminated();
	}

	public ManagedChannel getChannel() {
		return channel;
	}

	public void setChannel(ManagedChannel channel) {
		this.channel = channel;
	}
	public GoogleCredentials getCreds() {
		return creds;
	}
	public void setCreds(GoogleCredentials creds) {
		this.creds = creds;
	}
	public int getBytesPerSample() {
		return bytesPerSample;
	}
	public void setBytesPerSample(int bytesPerSample) {
		this.bytesPerSample = bytesPerSample;
	}
	public int getRecorderSampleRate() {
		return recorderSampleRate;
	}
	public void setRecorderSampleRate(int recorderSampleRate) {
		this.recorderSampleRate = recorderSampleRate;
	}

	public TextResponseClientService getTextService() {
		return textService;
	}

	public void setTextService(TextResponseClientService textService) {
		this.textService = textService;
	}
	
	private boolean isUniceActive() {
		long activeFor = System.currentTimeMillis() - activatedAt;
		return activeFor >= 0 && activeFor <= DURATION;
	}

	public void setVoiceClient(VoiceResponseClientService voiceClient) {
		this.voiceClient = voiceClient;
	}

//	public void setMopidyClient(MopidyClientWebSocketHandler mopidyClient) {
//		this.mopidyClient = mopidyClient;
//	}
	
}
