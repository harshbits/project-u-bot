
package com.bot.chat.ws.beans.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fulfillment implements Serializable
{

    @SerializedName("speech")
    @Expose
    private String speech;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("displayText")
    @Expose
    private String displayText;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = new ArrayList<Message>();
    private final static long serialVersionUID = 1L;

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public Fulfillment withSpeech(String speech) {
        this.speech = speech;
        return this;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Fulfillment withSource(String source) {
        this.source = source;
        return this;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public Fulfillment withDisplayText(String displayText) {
        this.displayText = displayText;
        return this;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Fulfillment withMessages(List<Message> messages) {
        this.messages = messages;
        return this;
    }

}
