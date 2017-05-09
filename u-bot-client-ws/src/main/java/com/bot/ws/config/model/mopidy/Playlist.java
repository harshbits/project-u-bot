package com.bot.ws.config.model.mopidy;

import java.io.Serializable;
import java.util.List;

public class Playlist implements Serializable {

	private static final long serialVersionUID = 1L;

	private String uri;
	private String name;
	private List<Track> tracks;

	public Playlist() {

	}

	public Playlist(String uri, String name, List<Track> tracks) {
		this.uri = uri;
		this.name = name;
		this.tracks = tracks;
	}

	public String getUri() {
		return uri;
	}

	public String getName() {
		return name;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Playlist && ((Playlist) obj).getUri().equals(uri);
	}
}
