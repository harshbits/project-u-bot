package com.bot.ws.mopidy.client;

import com.bot.ws.config.model.mopidy.PlaybackState;
import com.bot.ws.config.model.mopidy.TrackListTrack;

public interface MopidyPlayerController {
	void toggle();

	void stop();

	void play();

	void pause();

	void next();

	void previous();

	void shuffle();

	void clear();

	TrackListTrack getCurrentTrack();

	int getVolume();

	void setVolume(int volume);

	PlaybackState getPlaybackState();

	void addUri(String uri);
}
