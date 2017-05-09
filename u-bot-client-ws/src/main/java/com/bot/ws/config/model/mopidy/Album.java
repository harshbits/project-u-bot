package com.bot.ws.config.model.mopidy;

import java.io.Serializable;
import java.util.List;

public class Album implements Serializable {

	private static final long serialVersionUID = 1L;

	private String uri;
	private List<Artist> artists;
	private String name;
	private String date;

	public Album() {

	}

	public Album(String uri, List<Artist> artists, String name, String date) {
		this.uri = uri;
		this.artists = artists;
		this.name = name;
		this.date = date;
	}

	public String getUri() {
		return uri;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Album && ((Album) obj).getUri().equals(uri);
	}
}
