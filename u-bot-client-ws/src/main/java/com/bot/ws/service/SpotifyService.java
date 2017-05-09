package com.bot.ws.service;

import com.bot.ws.config.model.enums.MusicEnum;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Track;

public interface SpotifyService {

	public Page<Track> searchTracks(String input);
	
	public String getTrackUri(String input);
	
	public void play(String uri);
	
	public void resume();
	
	public void pause();
	
	public void stop();
	
	public void next();
	
	public void previous();
	
	public void shuffle();
	
	public void addFavorite();
	
	public void addPlaylist();
	
	public void executeMethod(MusicEnum command, String param, String artist) throws Exception;
}
