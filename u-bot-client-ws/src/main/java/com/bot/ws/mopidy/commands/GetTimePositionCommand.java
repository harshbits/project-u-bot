package com.bot.ws.mopidy.commands;

import com.bot.ws.mopidy.jsonrpc.Command;
import com.bot.ws.mopidy.jsonrpc.Method;
import com.google.gson.JsonElement;


public class GetTimePositionCommand extends Command<Integer> {
    public GetTimePositionCommand(ResponseListener<Integer> responseListener, ErrorListener errorListener) {
        super(Method.GET_TIME_POSITION.toString(), responseListener, errorListener);
    }

    @Override
    protected Response<Integer> parseResponse(JsonElement data) {
        return Response.success(data.getAsInt());
    }
}
