
package com.bot.chat.ws.beans;

import java.io.Serializable;

import com.bot.chat.ws.beans.support.Result;
import com.bot.chat.ws.beans.support.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookRequest implements Serializable {

	private final static long serialVersionUID = 1L;

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

	public WebhookRequest withId(String id) {
		this.id = id;
		return this;
	}

	public WebhookRequest withTimestamp(String timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public WebhookRequest withLang(String lang) {
		this.lang = lang;
		return this;
	}

	public WebhookRequest withResult(Result result) {
		this.result = result;
		return this;
	}

	public WebhookRequest withStatus(Status status) {
		this.status = status;
		return this;
	}

	public WebhookRequest withSessionId(String sessionId) {
		this.sessionId = sessionId;
		return this;
	}

}
