
package com.bot.chat.ws.beans;

import java.io.Serializable;

import com.bot.chat.ws.beans.support.Result;
import com.bot.chat.ws.beans.support.Status;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebhookRequest implements Serializable {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("timestamp")
	@Expose
	private String timestamp;
	@SerializedName("lang")
	@Expose
	private String lang;
	@SerializedName("result")
	@Expose
	private Result result;
	@SerializedName("status")
	@Expose
	private Status status;
	@SerializedName("sessionId")
	@Expose
	private String sessionId;
	private final static long serialVersionUID = 1L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public WebhookRequest withId(String id) {
		this.id = id;
		return this;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public WebhookRequest withTimestamp(String timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public WebhookRequest withLang(String lang) {
		this.lang = lang;
		return this;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public WebhookRequest withResult(Result result) {
		this.result = result;
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public WebhookRequest withStatus(Status status) {
		this.status = status;
		return this;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public WebhookRequest withSessionId(String sessionId) {
		this.sessionId = sessionId;
		return this;
	}

}
