package com.bot.ws.mopidy.commands;

import com.bot.ws.config.model.mopidy.TrackListTrack;
import com.bot.ws.mopidy.jsonrpc.Command;
import com.bot.ws.mopidy.jsonrpc.Method;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class GetTrackListTrackCommand extends Command<TrackListTrack> {
	public GetTrackListTrackCommand(ResponseListener<TrackListTrack> responseListener, ErrorListener errorListener) {
		super(Method.GET_CURRENT_TRACK.toString(), responseListener, errorListener);
	}

	@Override
	protected Response<TrackListTrack> parseResponse(JsonElement data) {
		return Response.success(new Gson().fromJson(data, TrackListTrack.class));
	}
}
