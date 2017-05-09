package com.bot.ws.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bot.ws.config.model.enums.MusicEnum;
import com.bot.ws.service.SpotifyService;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.TrackSearchRequest;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Track;

public class SpotifyServiceImpl implements SpotifyService {

	private static final Logger logger = LoggerFactory.getLogger(SpotifyServiceImpl.class);
	
	@Autowired
	private Api spotifyApi;

	@Autowired
	private MopidyClientWebSocketHandler mopidyClient;
	
	@Autowired
	private VoiceResponseClientServiceImpl voiceClient;
	
//	@Autowired
//	private AppProperties properties;
	
	private String lastPlayed = "";
	
//	private Mopidy mopidy;
	
	
	@Override
	public String getTrackUri(String input) {
		logger.info("Input track: " + input);
		if((input.isEmpty() || input == null) && (lastPlayed != null || !lastPlayed.isEmpty())){
			return lastPlayed;
		}
		if(!(input.isEmpty() || input == null)){
			Page<Track> tracks = searchTracks(input);
			if (tracks != null && tracks.getItems().size() > 0) {
				this.lastPlayed = tracks.getItems().get(0).getUri();
				return lastPlayed;
			}
		}
		return null;
	}
	
	@Override
	public Page<Track> searchTracks(String input) {
		
		logger.info("Track Search Input: "+ input);
		try {
			
//			PlaylistRequest request = spotifyApi.getPlaylist("harshbits", "spotify test").build();
//			AlbumSearchRequest request = spotifyApi.searchAlbums("spotify test").market("US").build();
			final TrackSearchRequest request = spotifyApi.searchTracks(input).market("US").build();
			final Page<Track> trackSearchResult = request.get();
			return trackSearchResult;
		} catch (Exception e) {
			logger.error("Search Track Error: {}", e.getMessage());
			return null;
		}
	}

	@Override
	public void resume() {
		mopidyClient.getSpotifyPlayer().connect();
		mopidyClient.getSpotifyPlayer().play();
//		initMopidy();
//		mopidy.play();
		logger.info("Continued playing");
	}
	
	@Override
	public void play(String  uri) {
		if(uri != null){
			
			mopidyClient.getSpotifyPlayer().connect();
			mopidyClient.getSpotifyPlayer().stop();
			mopidyClient.getSpotifyPlayer().clear();
			mopidyClient.getSpotifyPlayer().setVolume(50);
			mopidyClient.getSpotifyPlayer().addUri(uri);
			mopidyClient.getSpotifyPlayer().shuffle();
			mopidyClient.getSpotifyPlayer().play();
			mopidyClient.getSpotifyPlayer().toggle();
//			initMopidy();
//			mopidy.stop();
//			mopidy.clear();
//			mopidy.setVolume(50);
//			mopidy.addUri(uri);
//			mopidy.shuffle();
//			mopidy.play();
//			mopidy.toggle();
			logger.info("Started playing: {}", uri);
			
		}else{
			voiceClient.playResponse("Sorry. No matching track found");
			logger.error("No Track found");
		}
	}

	@Override
	public void pause() {
		mopidyClient.getSpotifyPlayer().connect();
		mopidyClient.getSpotifyPlayer().pause();
//		initMopidy();
//		mopidy.pause();
		logger.info("Paused playing");
	}

	@Override
	public void stop() {
		mopidyClient.getSpotifyPlayer().connect();
		mopidyClient.getSpotifyPlayer().stop();
//		initMopidy();
//		mopidy.stop();
		logger.info("Stopped playing");
	}

	@Override
	public void next() {
		mopidyClient.getSpotifyPlayer().connect();
		mopidyClient.getSpotifyPlayer().next();
//		initMopidy();
//		mopidy.next();
		logger.info("Next playing");
	}

	@Override
	public void previous() {
		mopidyClient.getSpotifyPlayer().connect();
		mopidyClient.getSpotifyPlayer().previous();
//		initMopidy();
//		mopidy.previous();
		logger.info("Previous playing");
	}

	@Override
	public void shuffle() {	
//		mopidyClient.getSpotifyPlayer().connect();;
//		mopidyClient.getSpotifyPlayer().shuffle();
//		initMopidy();
//		mopidy.shuffle();
	}

	@Override
	public void addFavorite() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPlaylist() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeMethod(MusicEnum command, String song, String artist) throws Exception {
		
		switch(command){
		case PLAY:
			String uri = getTrackUri(song);
			play(uri);
			break;
			
		case PAUSE:
			pause();
			break;
		
		case RESUME:
			resume();
			break;
			
		case STOP:
			stop();
			break;
		default:
			break;
		}
		
	}

	
//	private void initMopidy() {
//		URI uri;
//		try {
////			uri = new URI(properties.getMopidyWebSocketUrl());
////			uri = new URI("wss://localhost:6680/mopidy/ws/");
////			mopidy = new Mopidy(new MopidyWebSocket(uri));
//			mopidy = new Mopidy(new MopidyWebSocket(new URI("wss://localhost:6680/mopidy/ws/")));
//			mopidy.connect();
//			
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private class MopidyWebSocket extends MopidySocket {
//	    private final URI uri;
//	    private MopidyWebSocket.WebSocket webSocket;
//
//	    public MopidyWebSocket(URI uri) {
//	        this.uri = uri;
//	    }
//
//	    @Override
//	    protected void connect() {
//	        if (webSocket != null) {
//	            webSocket.close();
//	        }
//
//	        webSocket = new MopidyWebSocket.WebSocket(uri);
//	        webSocket.connect();
//	    }
//
//	    @Override
//	    protected void disconnect() {
//	        if (webSocket != null) {
//	            webSocket.close();
//	        }
//	    }
//
//	    @Override
//	    public void sendData(String data) {
//	        if (webSocket != null) {
//	            webSocket.send(data);
//	        }
//	    }
//
//	    public class WebSocket extends WebSocketClient {
//
//	        public WebSocket(URI serverURI) {
//	            super(serverURI);
//	        }
//
//	        @Override
//	        public void onOpen(ServerHandshake handshakedata) {
//	            onConnected();
//	        }
//
//	        @Override
//	        public void onMessage(String message) {
//	            onDataReceived(message);
//	        }
//
//	        @Override
//	        public void onClose(int code, String reason, boolean remote) {
//	            onDisconnected(false);
//	        }
//
//	        @Override
//	        public void onError(Exception ex) {
//	            MopidyWebSocket.this.onError(ex);
//	        }
//	    }
//	}
}
