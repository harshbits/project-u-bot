
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
public class Parameters_ implements Serializable {

	private final static long serialVersionUID = 1L;

	@SerializedName("date-time.original")
	@Expose
	private String dateTimeOriginal;

	@SerializedName("unit.original")
	@Expose
	private String unitOriginal;

	@SerializedName("unit")
	@Expose
	private String unit;

	@SerializedName("geo-city")
	@Expose
	private String geoCity;

	@SerializedName("date-time")
	@Expose
	private List<String> dateTime;

	@SerializedName("geo-city.original")
	@Expose
	private String geoCityOriginal;

	@SerializedName("address.original")
	@Expose
	private String addressOriginal;

	public Parameters_ withDateTimeOriginal(String dateTimeOriginal) {
		this.dateTimeOriginal = dateTimeOriginal;
		return this;
	}

	public Parameters_ withUnitOriginal(String unitOriginal) {
		this.unitOriginal = unitOriginal;
		return this;
	}

	public Parameters_ withUnit(String unit) {
		this.unit = unit;
		return this;
	}

	public Parameters_ withGeoCity(String geoCity) {
		this.geoCity = geoCity;
		return this;
	}

	public Parameters_ withDateTime(List<String> dateTime) {
		this.dateTime = dateTime;
		return this;
	}

	public Parameters_ withGeoCityOriginal(String geoCityOriginal) {
		this.geoCityOriginal = geoCityOriginal;
		return this;
	}

	public Parameters_ withAddressOriginal(String addressOriginal) {
		this.addressOriginal = addressOriginal;
		return this;
	}

}
