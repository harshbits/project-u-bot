package com.bot.ws.config.model.mopidy;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class TrackListId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@SerializedName("tlid")
    private int id;

    public TrackListId() {

    }

    public TrackListId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
