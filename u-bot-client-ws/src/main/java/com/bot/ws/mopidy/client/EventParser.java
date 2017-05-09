package com.bot.ws.mopidy.client;

import java.util.ArrayList;
import java.util.List;

import com.bot.ws.config.model.mopidy.PlaybackState;
import com.bot.ws.config.model.mopidy.TrackListTrack;
import com.bot.ws.mopidy.events.Event;
import com.bot.ws.mopidy.events.EventListener;
import com.bot.ws.mopidy.events.PlaybackStateEvent;
import com.bot.ws.mopidy.events.TrackPlaybackStateEvent;
import com.bot.ws.mopidy.events.VolumeChangedEvent;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class EventParser implements DataParser {

	private static final String KEY_EVENT = "event";
	private static final String KEY_TRACK = "tl_track";
	private static final String KEY_STATE = "new_state";
	private static final String KEY_VOLUME = "volume";

	private List<EventListener> eventListenerList = new ArrayList<>();

	public Event parse(String data) {
		JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
		if (!jsonObject.has(KEY_EVENT))
			return null;
		String eventName = jsonObject.get(KEY_EVENT).getAsString();
		Event.Type type = Event.Type.valueOf(eventName.toUpperCase());

		if (type == Event.Type.PLAYBACK_STATE_CHANGED) {
			if (jsonObject.has(KEY_STATE)) {
				PlaybackState state = PlaybackState.valueOf(jsonObject.get(KEY_STATE).getAsString().toUpperCase());
				PlaybackStateEvent event = new PlaybackStateEvent(state);
				sendEvent(event);
				return event;
			}
		} else if (TrackPlaybackStateEvent.validEvent(type)) {
			if (jsonObject.has(KEY_TRACK)) {
				JsonElement trackJson = jsonObject.get(KEY_TRACK);
				TrackListTrack trackListTrack = new Gson().fromJson(trackJson, TrackListTrack.class);
				TrackPlaybackStateEvent event = new TrackPlaybackStateEvent(type, trackListTrack);
				sendEvent(event);
				return event;
			}
		} else if (type == Event.Type.VOLUME_CHANGED) {
			if (jsonObject.has(KEY_VOLUME)) {
				VolumeChangedEvent event = new VolumeChangedEvent(jsonObject.get(KEY_VOLUME).getAsInt());
				sendEvent(event);
				return event;
			}
		}

		return new Event(type);
	}

	private void sendEvent(PlaybackStateEvent event) {
		for (EventListener eventListener : eventListenerList) {
			eventListener.onPlaybackState(event);
		}
	}

	private void sendEvent(TrackPlaybackStateEvent event) {
		for (EventListener eventListener : eventListenerList) {
			eventListener.onTrackPlaybackState(event);
		}
	}

	private void sendEvent(VolumeChangedEvent event) {
		for (EventListener eventListener : eventListenerList) {
			eventListener.onVolumeChanged(event);
		}
	}

	public void addEventListener(EventListener eventListener) {
		this.eventListenerList.add(eventListener);
	}

	public void removeEventListener(EventListener eventListener) {
		this.eventListenerList.remove(eventListener);
	}

	@Override
	public void onDataReceived(String data) {
		parse(data);
	}
}
