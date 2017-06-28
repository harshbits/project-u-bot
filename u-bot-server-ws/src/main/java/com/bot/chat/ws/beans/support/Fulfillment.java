
package com.bot.chat.ws.beans.support;

import java.io.Serializable;
import java.util.List;

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
public class Fulfillment implements Serializable {

	private final static long serialVersionUID = 1L;

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
	private List<Message> messages;

	public Fulfillment withSpeech(String speech) {
		this.speech = speech;
		return this;
	}

	public Fulfillment withSource(String source) {
		this.source = source;
		return this;
	}

	public Fulfillment withDisplayText(String displayText) {
		this.displayText = displayText;
		return this;
	}

	public Fulfillment withMessages(List<Message> messages) {
		this.messages = messages;
		return this;
	}

}
