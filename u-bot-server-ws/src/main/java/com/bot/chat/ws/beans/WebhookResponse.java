package com.bot.chat.ws.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private String speech;

	private String displayText;

	private final String source = "java-webhook";

	public WebhookResponse(){
		
	}
	public WebhookResponse(String speech, String displayText) {
		this.speech = speech;
		this.displayText = displayText;
	}

	public String getSpeech() {
		return speech;
	}

	public String getDisplayText() {
		return displayText;
	}

	public String getSource() {
		return source;
	}

	public void setSpeech(String speech) {
		this.speech = speech;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}


}