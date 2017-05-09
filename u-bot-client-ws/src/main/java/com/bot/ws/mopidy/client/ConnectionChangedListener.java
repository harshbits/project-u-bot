package com.bot.ws.mopidy.client;

public interface ConnectionChangedListener {
	void onConnected();

	void onDisconnected();
}
