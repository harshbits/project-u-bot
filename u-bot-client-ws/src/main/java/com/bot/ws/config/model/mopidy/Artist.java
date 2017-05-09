package com.bot.ws.config.model.mopidy;

import java.io.Serializable;

public class Artist implements Serializable {
	private static final long serialVersionUID = 1L;
	private String uri;
	private String name;

	public Artist() {

	}

	public Artist(String uri, String name) {
		this.uri = uri;
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Artist && ((Artist) obj).getUri().equals(uri);
	}
}
