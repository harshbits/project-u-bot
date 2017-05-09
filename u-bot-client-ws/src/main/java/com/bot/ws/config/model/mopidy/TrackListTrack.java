package com.bot.ws.config.model.mopidy;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class TrackListTrack implements Serializable {

	private static final long serialVersionUID = 1L;

	private Track track;
	@SerializedName("tlid")
	private int id;

	public TrackListTrack() {
	}

	public TrackListTrack(Track track, int id) {
		this.track = track;
		this.id = id;
	}

	public Track getTrack() {
		return track;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return id + ": " + track.toString();
	}
}
