
package com.bot.chat.ws.beans.support;

import java.io.Serializable;
import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Context implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("parameters")
    @Expose
//    private Parameters_ parameters;
    private HashMap<String, Object> parameters;
    @SerializedName("lifespan")
    @Expose
    private long lifespan;
    private final static long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Context withName(String name) {
        this.name = name;
        return this;
    }

//    public Parameters_ getParameters() {
//        return parameters;
//    }
//
//    public void setParameters(Parameters_ parameters) {
//        this.parameters = parameters;
//    }
//
//    public Context withParameters(Parameters_ parameters) {
//        this.parameters = parameters;
//        return this;
//    }
    
    public HashMap<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Context withParameters(HashMap<String, Object> parameters) {
        this.parameters = parameters;
        return this;
    }

    public long getLifespan() {
        return lifespan;
    }

    public void setLifespan(long lifespan) {
        this.lifespan = lifespan;
    }

    public Context withLifespan(long lifespan) {
        this.lifespan = lifespan;
        return this;
    }

}
