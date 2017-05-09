package com.bot.ws.mopidy.commands;

import java.lang.reflect.Type;
import java.util.List;

import com.bot.ws.config.model.mopidy.TrackListTrack;
import com.bot.ws.mopidy.jsonrpc.Command;
import com.bot.ws.mopidy.jsonrpc.Method;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Created by wouter on 16-9-16.
 */
public class GetTrackList extends Command<List<TrackListTrack>> {

	public GetTrackList(ResponseListener<List<TrackListTrack>> responseListener, ErrorListener errorListener) {
		super(Method.GET_TRACK_LIST.toString(), responseListener, errorListener);
	}

	@Override
	protected Response<List<TrackListTrack>> parseResponse(JsonElement data) {
		Type listType = new TypeToken<List<TrackListTrack>>() {
		}.getType();
		Gson gson = new Gson();
		List<TrackListTrack> list = gson.fromJson(data, listType);
		return Response.success(list);
	}
}
