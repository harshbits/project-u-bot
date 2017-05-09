
package com.bot.chat.ws.beans.support;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message implements Serializable
{

    @SerializedName("type")
    @Expose
    private long type;
    @SerializedName("speech")
    @Expose
    private String speech;
    private final static long serialVersionUID = 1L;

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public Message withType(long type) {
        this.type = type;
        return this;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public Message withSpeech(String speech) {
        this.speech = speech;
        return this;
    }

}
