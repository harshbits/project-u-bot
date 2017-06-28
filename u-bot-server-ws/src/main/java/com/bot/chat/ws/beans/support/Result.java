
package com.bot.chat.ws.beans.support;

import java.io.Serializable;
import java.util.HashMap;
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
public class Result implements Serializable {
	private final static long serialVersionUID = 1L;

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
	private HashMap<String, Object> parameters;

	@SerializedName("contexts")
	@Expose
	private List<Context> contexts;

	@SerializedName("metadata")
	@Expose
	private Metadata metadata;

	@SerializedName("fulfillment")
	@Expose
	private Fulfillment fulfillment;

	@SerializedName("score")
	@Expose
	private long score;

	public Result withSource(String source) {
		this.source = source;
		return this;
	}

	public Result withResolvedQuery(String resolvedQuery) {
		this.resolvedQuery = resolvedQuery;
		return this;
	}

	public Result withAction(String action) {
		this.action = action;
		return this;
	}

	public Result withActionIncomplete(boolean actionIncomplete) {
		this.actionIncomplete = actionIncomplete;
		return this;
	}

	public Result withParameters(HashMap<String, Object> parameters) {
		this.parameters = parameters;
		return this;
	}

	public Result withContexts(List<Context> contexts) {
		this.contexts = contexts;
		return this;
	}

	public Result withMetadata(Metadata metadata) {
		this.metadata = metadata;
		return this;
	}

	public Result withFulfillment(Fulfillment fulfillment) {
		this.fulfillment = fulfillment;
		return this;
	}

	public Result withScore(long score) {
		this.score = score;
		return this;
	}

}
