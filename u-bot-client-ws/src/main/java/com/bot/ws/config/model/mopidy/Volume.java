package com.bot.ws.config.model.mopidy;

import java.io.Serializable;

public class Volume implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int volume;

	public Volume() {

	}

	public Volume(int volume) {
		this.volume = volume;
	}

	public int getVolume() {
		return volume;
	}

	@Override
	public String toString() {
		return String.valueOf(volume);
	}
}
