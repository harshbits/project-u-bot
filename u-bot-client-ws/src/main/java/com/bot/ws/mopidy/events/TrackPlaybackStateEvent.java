package com.bot.ws.mopidy.events;

import com.bot.ws.config.model.mopidy.TrackListTrack;

public class TrackPlaybackStateEvent extends Event {

	private final State state;
	private final TrackListTrack trackListTrack;

	public TrackPlaybackStateEvent(Type type, TrackListTrack trackListTrack) {
		super(type);
		this.state = State.valueOf(type);
		this.trackListTrack = trackListTrack;
	}

	public static boolean validEvent(Type type) {
		try {
			State state = State.valueOf(type);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	public State getState() {
		return state;
	}

	public TrackListTrack getTrackListTrack() {
		return trackListTrack;
	}

	@Override
	public String toString() {
		return "Track " + state.toString().toLowerCase() + ": " + trackListTrack.toString();
	}

	public enum State {
		STARTED, ENDED, PAUSED, RESUMED;

		public static State valueOf(Event.Type eventType) {
			if (!(eventType == Type.TRACK_PLAYBACK_STARTED || eventType == Type.TRACK_PLAYBACK_ENDED
					|| eventType == Type.TRACK_PLAYBACK_RESUMED || eventType == Type.TRACK_PLAYBACK_PAUSED)) {
				throw new IllegalArgumentException(
						"No enum const " + State.class.getSimpleName() + "." + eventType.toString());
			}

			String stateName = eventType.toString().substring("track_playback_".length());
			return valueOf(stateName.toUpperCase());
		}
	}
}
