package com.bot.ws.mopidy.events;

import com.bot.ws.config.model.mopidy.PlaybackState;


public class PlaybackStateEvent extends Event {

    private final PlaybackState state;

    public PlaybackStateEvent(PlaybackState state) {
        super(Type.PLAYBACK_STATE_CHANGED);
        this.state = state;
    }

    public PlaybackState getState() {
        return state;
    }

    @Override
    public String toString() {
        return "State: " + state;
    }
}
