package com.bot.ws.mopidy.events;

public interface EventListener {
	void onPlaybackState(PlaybackStateEvent event);

	void onTrackPlaybackState(TrackPlaybackStateEvent event);

	void onVolumeChanged(VolumeChangedEvent event);
}
