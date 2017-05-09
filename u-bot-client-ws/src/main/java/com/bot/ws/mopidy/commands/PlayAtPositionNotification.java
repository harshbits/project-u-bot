package com.bot.ws.mopidy.commands;

import com.bot.ws.config.model.mopidy.TrackListId;
import com.bot.ws.mopidy.jsonrpc.Method;
import com.bot.ws.mopidy.jsonrpc.Notification;

public class PlayAtPositionNotification extends Notification {
	private final TrackListId params;

	public PlayAtPositionNotification(int position) {
		super(Method.PLAY);
		this.params = new TrackListId(position);
	}
}
