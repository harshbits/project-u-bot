
package com.bot.chat.ws.beans.support;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status implements Serializable
{

    @SerializedName("code")
    @Expose
    private long code;
    @SerializedName("errorType")
    @Expose
    private String errorType;
    private final static long serialVersionUID = 1L;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public Status withCode(long code) {
        this.code = code;
        return this;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public Status withErrorType(String errorType) {
        this.errorType = errorType;
        return this;
    }

}
