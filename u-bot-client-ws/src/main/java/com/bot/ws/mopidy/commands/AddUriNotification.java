package com.bot.ws.mopidy.commands;

import com.bot.ws.mopidy.jsonrpc.Method;
import com.bot.ws.mopidy.jsonrpc.Notification;

/**
 * Created by wouter on 21-2-17.
 */
public class AddUriNotification extends Notification {

	private final TrackUri params;

	public AddUriNotification(String uri) {
		super(Method.ADD_URI);
		this.params = new TrackUri(uri);
	}

	public class TrackUri {
		private final String uri;

		public TrackUri(String uri) {
			this.uri = uri;
		}

		public String getUri() {
			return uri;
		}

		@Override
		public String toString() {
			return uri;
		}
	}
}
