
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
public class Parameters implements Serializable {

	private final static long serialVersionUID = 1L;

	@SerializedName("address")
	@Expose
	private String address;

	@SerializedName("date-time")
	@Expose
	private List<String> dateTime;

	@SerializedName("geo-city")
	@Expose
	private String geoCity;

	@SerializedName("unit")
	@Expose
	private String unit;

	public Parameters withAddress(String address) {
		this.address = address;
		return this;
	}

	public Parameters withDateTime(List<String> dateTime) {
		this.dateTime = dateTime;
		return this;
	}

	public Parameters withGeoCity(String geoCity) {
		this.geoCity = geoCity;
		return this;
	}

	public Parameters withUnit(String unit) {
		this.unit = unit;
		return this;
	}

}
