package com.bot.ws.mopidy.jsonrpc;


public enum Method {
    PLAY("core.playback.play"),
    PAUSE("core.playback.pause"),
    RESUME("core.playback.resume"),
    STOP("core.playback.stop"),
    NEXT("core.playback.next"),
    PREVIOUS("core.playback.previous"),
    GET_STATE("core.playback.get_state"),

    SHUFFLE("core.tracklist.shuffle"),
    CLEAR("core.tracklist.clear"),
    ADD_URI("core.tracklist.add"),

    SET_VOLUME("core.playback.set_volume"),
    GET_VOLUME("core.playback.get_volume"),

    GET_CURRENT_TRACK("core.playback.get_current_tl_track"),
    GET_TIME_POSITION("core.playback.get_time_position"),

    GET_TRACK_LIST("core.tracklist.get_tl_tracks");

    private String methodName;

    Method(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return methodName;
    }
}
