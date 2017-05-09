package com.bot.ws.mopidy.commands;

import com.bot.ws.mopidy.jsonrpc.Command;
import com.bot.ws.mopidy.jsonrpc.Method;
import com.google.gson.JsonElement;

public class GetVolumeCommand extends Command<Integer> {
	public GetVolumeCommand(ResponseListener<Integer> responseListener, ErrorListener errorListener) {
		super(Method.GET_VOLUME.toString(), responseListener, errorListener);
	}

	@Override
	public Response parseResponse(JsonElement data) {
		return Response.success(data.getAsInt());
	}
}
