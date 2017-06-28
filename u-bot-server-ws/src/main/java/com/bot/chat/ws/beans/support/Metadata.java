
package com.bot.chat.ws.beans.support;

import java.io.Serializable;

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
public class Metadata implements Serializable {

	private final static long serialVersionUID = 1L;

	@SerializedName("intentId")
	@Expose
	private String intentId;

	@SerializedName("webhookUsed")
	@Expose
	private String webhookUsed;

	@SerializedName("webhookForSlotFillingUsed")
	@Expose
	private String webhookForSlotFillingUsed;

	@SerializedName("intentName")
	@Expose
	private String intentName;

	public Metadata withIntentId(String intentId) {
		this.intentId = intentId;
		return this;
	}

	public Metadata withWebhookUsed(String webhookUsed) {
		this.webhookUsed = webhookUsed;
		return this;
	}

	public Metadata withWebhookForSlotFillingUsed(String webhookForSlotFillingUsed) {
		this.webhookForSlotFillingUsed = webhookForSlotFillingUsed;
		return this;
	}

	public Metadata withIntentName(String intentName) {
		this.intentName = intentName;
		return this;
	}

}
