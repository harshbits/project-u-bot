
package com.bot.chat.ws.beans.support;

import java.io.Serializable;
import java.util.HashMap;

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
public class Context implements Serializable {
	
	private final static long serialVersionUID = 1L;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("parameters")
	@Expose
	private HashMap<String, Object> parameters;

	@SerializedName("lifespan")
	@Expose
	private long lifespan;

	public Context withName(String name) {
		this.name = name;
		return this;
	}

	public Context withParameters(HashMap<String, Object> parameters) {
		this.parameters = parameters;
		return this;
	}

	public Context withLifespan(long lifespan) {
		this.lifespan = lifespan;
		return this;
	}

}
