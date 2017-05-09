package com.bot.ws.mopidy.client;

import com.bot.ws.config.model.mopidy.PlaybackState;
import com.bot.ws.config.model.mopidy.TrackListTrack;
import com.bot.ws.mopidy.events.EventListener;
import com.bot.ws.mopidy.events.PlaybackStateEvent;
import com.bot.ws.mopidy.events.TrackPlaybackStateEvent;
import com.bot.ws.mopidy.events.VolumeChangedEvent;
import com.bot.ws.mopidy.jsonrpc.Command;

class MopidyState implements EventListener {
	private PlaybackState playbackState = null;
	private TrackListTrack trackListTrack = null;
	private int volume = -1;
	private long lastUpdate;

	private TrackResponseListener trackResponseListener = new TrackResponseListener();
	private VolumeResponseListener volumeResponseListener = new VolumeResponseListener();
	private PlaybackStateResponseListener playbackStateResponseListener = new PlaybackStateResponseListener();

	private UpdateCompletedListener updateCompletedListener;
	private boolean didSendUpdate = false;

	MopidyState() {

	}

	void update(PlaybackState playbackState) {
		this.playbackState = playbackState;
		lastUpdate = System.currentTimeMillis();
	}

	void update(TrackListTrack trackListTrack) {
		this.trackListTrack = trackListTrack;
		lastUpdate = System.currentTimeMillis();
	}

	void update(int volume) {
		this.volume = volume;
		lastUpdate = System.currentTimeMillis();
	}

	public void setPlaybackState(PlaybackState playbackState){
		this.playbackState = playbackState;
	}
	
	PlaybackState getPlaybackState() {
		return playbackState;
	}

	TrackListTrack getTrackListTrack() {
		return trackListTrack;
	}

	int getVolume() {
		return volume;
	}

	long getLastUpdate() {
		return lastUpdate;
	}

	@Override
	public void onPlaybackState(PlaybackStateEvent event) {
		update(event.getState());
	}

	@Override
	public void onTrackPlaybackState(TrackPlaybackStateEvent event) {
		update(event.getTrackListTrack());
	}

	@Override
	public void onVolumeChanged(VolumeChangedEvent event) {
		update(event.getVolume());
	}

	Command.ResponseListener<TrackListTrack> getTrackResponseListener() {
		return trackResponseListener;
	}

	Command.ResponseListener<Integer> getVolumeResponseListener() {
		return volumeResponseListener;
	}

	Command.ResponseListener<PlaybackState> getPlaybackStateResponseListener() {
		return playbackStateResponseListener;
	}

	void setUpdateCompletedListener(UpdateCompletedListener updateCompletedListener) {
		this.updateCompletedListener = updateCompletedListener;
	}

	private void checkState() {
		if (!didSendUpdate && playbackState != null && trackListTrack != null && volume != -1) {
			if (updateCompletedListener != null)
				updateCompletedListener.onUpdateCompleted();
			didSendUpdate = true;
		}
	}

	void reset() {
		didSendUpdate = false;
		playbackState = null;
		trackListTrack = null;
		volume = -1;
	}

	interface UpdateCompletedListener {
		void onUpdateCompleted();
	}

	private class TrackResponseListener implements Command.ResponseListener<TrackListTrack> {

		@Override
		public void onResponse(TrackListTrack response) {
			update(response);
			checkState();
		}
	}

	private class VolumeResponseListener implements Command.ResponseListener<Integer> {

		@Override
		public void onResponse(Integer response) {
			update(response);
			checkState();
		}
	}

	private class PlaybackStateResponseListener implements Command.ResponseListener<PlaybackState> {

		@Override
		public void onResponse(PlaybackState response) {
			update(response);
			checkState();
		}
	}
}
