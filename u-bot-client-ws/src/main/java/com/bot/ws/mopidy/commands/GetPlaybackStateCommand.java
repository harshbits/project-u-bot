package com.bot.ws.mopidy.commands;

import com.bot.ws.config.model.mopidy.PlaybackState;
import com.bot.ws.mopidy.jsonrpc.Command;
import com.bot.ws.mopidy.jsonrpc.Method;
import com.google.gson.JsonElement;



public class GetPlaybackStateCommand extends Command<PlaybackState> {
    public GetPlaybackStateCommand(ResponseListener<PlaybackState> responseListener, ErrorListener errorListener) {
        super(Method.GET_STATE.toString(), responseListener, errorListener);
    }

    @Override
    protected Response<PlaybackState> parseResponse(JsonElement data) {
        String stateString = data.getAsString();
        return Response.success(PlaybackState.valueOf(stateString.toUpperCase()));
    }
}
