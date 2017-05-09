package com.bot.ws.mopidy.jsonrpc;

public class Notification extends JsonRPCCommand {

	public Notification(Method method) {
		super(method.toString());
	}
}
