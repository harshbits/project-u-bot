package com.bot.ws.mopidy.events;

/**
 * Created by wouter on 28-8-16.
 */
public class VolumeChangedEvent extends Event {

	private final int volume;

	public VolumeChangedEvent(int volume) {
		super(Type.VOLUME_CHANGED);
		this.volume = volume;
	}

	public int getVolume() {
		return volume;
	}

	@Override
	public String toString() {
		return "Volume: " + volume;
	}
}
