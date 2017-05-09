
package com.bot.chat.ws.beans.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Serializable
{

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("resolvedQuery")
    @Expose
    private String resolvedQuery;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("actionIncomplete")
    @Expose
    private boolean actionIncomplete;
    @SerializedName("parameters")
    @Expose
//    private Parameters parameters;
    private HashMap<String, Object> parameters;
    
    @SerializedName("contexts")
    @Expose
    private List<Context> contexts = new ArrayList<Context>();
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("fulfillment")
    @Expose
    private Fulfillment fulfillment;
    @SerializedName("score")
    @Expose
    private long score;
    private final static long serialVersionUID = 1L;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Result withSource(String source) {
        this.source = source;
        return this;
    }

    public String getResolvedQuery() {
        return resolvedQuery;
    }

    public void setResolvedQuery(String resolvedQuery) {
        this.resolvedQuery = resolvedQuery;
    }

    public Result withResolvedQuery(String resolvedQuery) {
        this.resolvedQuery = resolvedQuery;
        return this;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Result withAction(String action) {
        this.action = action;
        return this;
    }

    public boolean isActionIncomplete() {
        return actionIncomplete;
    }

    public void setActionIncomplete(boolean actionIncomplete) {
        this.actionIncomplete = actionIncomplete;
    }

    public Result withActionIncomplete(boolean actionIncomplete) {
        this.actionIncomplete = actionIncomplete;
        return this;
    }

//    public Parameters getParameters() {
//        return parameters;
//    }
//
//    public void setParameters(Parameters parameters) {
//        this.parameters = parameters;
//    }
//    public Result withParameters(Parameters parameters) {
//        this.parameters = parameters;
//        return this;
//    }
    
    public Result withParameters(HashMap<String, Object> parameters) {
        this.parameters = parameters;
        return this;
    }

    public HashMap<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(HashMap<String, Object> parameters) {
		this.parameters = parameters;
	}
	

	public List<Context> getContexts() {
        return contexts;
    }

    public void setContexts(List<Context> contexts) {
        this.contexts = contexts;
    }

    public Result withContexts(List<Context> contexts) {
        this.contexts = contexts;
        return this;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Result withMetadata(Metadata metadata) {
        this.metadata = metadata;
        return this;
    }

    public Fulfillment getFulfillment() {
        return fulfillment;
    }

    public void setFulfillment(Fulfillment fulfillment) {
        this.fulfillment = fulfillment;
    }

    public Result withFulfillment(Fulfillment fulfillment) {
        this.fulfillment = fulfillment;
        return this;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public Result withScore(long score) {
        this.score = score;
        return this;
    }

}
