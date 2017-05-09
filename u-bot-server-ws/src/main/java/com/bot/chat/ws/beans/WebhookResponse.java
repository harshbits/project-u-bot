package com.bot.chat.ws.beans;

public class WebhookResponse {

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