package com.bot.ws.config.model.mopidy;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Track implements Serializable {

	private static final long serialVersionUID = 1L;

	private String uri;
	private List<Artist> artists;
	private Album album;
	private String name;
	@SerializedName("track_no")
	private int trackNumber;
	private String date;
	private int bitrate;
	private int length;

	public Track() {

	}

	public Track(String uri, List<Artist> artists, Album album, String name, int trackNumber, String date, int bitrate,
			int length) {
		this.uri = uri;
		this.artists = artists;
		this.album = album;
		this.name = name;
		this.trackNumber = trackNumber;
		this.date = date;
		this.bitrate = bitrate;
		this.length = length;
	}

	public String getUri() {
		return uri;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public Album getAlbum() {
		return album;
	}

	public String getName() {
		return name;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public String getDate() {
		return date;
	}

	public int getBitrate() {
		return bitrate;
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getArtistNames() {
		StringBuilder stringBuilder = new StringBuilder();
		String prefix = "";
		for (Artist artist : artists) {
			stringBuilder.append(prefix);
			prefix = ", ";
			stringBuilder.append(artist.getName());
		}
		return stringBuilder.toString();
	}

	@Override
	public String toString() {
		return getArtistNames() + " - " + name;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Track && ((Track) obj).getUri().equals(uri);
	}
}
