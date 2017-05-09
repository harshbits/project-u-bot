package com.bot.ws.mopidy.events;


public class Event {

    private final Type type;

    public Event(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public enum Type {
        MUTE_CHANGED,
        OPTIONS_CHANGED,
        PLAYBACK_STATE_CHANGED,
        PLAYLIST_CHANGED,
        PLAYLISTS_LOADED,
        SEEKED,
        TRACK_PLAYBACK_ENDED,
        TRACK_PLAYBACK_PAUSED,
        TRACK_PLAYBACK_RESUMED,
        TRACK_PLAYBACK_STARTED,
        TRACKLIST_CHANGED,
        VOLUME_CHANGED
    }
}
