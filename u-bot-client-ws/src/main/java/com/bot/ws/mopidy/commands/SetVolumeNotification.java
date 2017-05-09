package com.bot.ws.mopidy.commands;

import com.bot.ws.config.model.mopidy.Volume;
import com.bot.ws.mopidy.jsonrpc.Method;
import com.bot.ws.mopidy.jsonrpc.Notification;

public class SetVolumeNotification extends Notification {
	private final Volume params;

	public SetVolumeNotification(int volume) {
		super(Method.SET_VOLUME);
		this.params = new Volume(volume);
	}
}
