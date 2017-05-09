
package com.bot.chat.ws.beans.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parameters_ implements Serializable
{

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
    private List<String> dateTime = new ArrayList<String>();
    @SerializedName("geo-city.original")
    @Expose
    private String geoCityOriginal;
    @SerializedName("address.original")
    @Expose
    private String addressOriginal;
    private final static long serialVersionUID = 1L;

    public String getDateTimeOriginal() {
        return dateTimeOriginal;
    }

    public void setDateTimeOriginal(String dateTimeOriginal) {
        this.dateTimeOriginal = dateTimeOriginal;
    }

    public Parameters_ withDateTimeOriginal(String dateTimeOriginal) {
        this.dateTimeOriginal = dateTimeOriginal;
        return this;
    }

    public String getUnitOriginal() {
        return unitOriginal;
    }

    public void setUnitOriginal(String unitOriginal) {
        this.unitOriginal = unitOriginal;
    }

    public Parameters_ withUnitOriginal(String unitOriginal) {
        this.unitOriginal = unitOriginal;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Parameters_ withUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getGeoCity() {
        return geoCity;
    }

    public void setGeoCity(String geoCity) {
        this.geoCity = geoCity;
    }

    public Parameters_ withGeoCity(String geoCity) {
        this.geoCity = geoCity;
        return this;
    }

    public List<String> getDateTime() {
        return dateTime;
    }

    public void setDateTime(List<String> dateTime) {
        this.dateTime = dateTime;
    }

    public Parameters_ withDateTime(List<String> dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getGeoCityOriginal() {
        return geoCityOriginal;
    }

    public void setGeoCityOriginal(String geoCityOriginal) {
        this.geoCityOriginal = geoCityOriginal;
    }

    public Parameters_ withGeoCityOriginal(String geoCityOriginal) {
        this.geoCityOriginal = geoCityOriginal;
        return this;
    }

    public String getAddressOriginal() {
        return addressOriginal;
    }

    public void setAddressOriginal(String addressOriginal) {
        this.addressOriginal = addressOriginal;
    }

    public Parameters_ withAddressOriginal(String addressOriginal) {
        this.addressOriginal = addressOriginal;
        return this;
    }

}
