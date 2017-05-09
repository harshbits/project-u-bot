
package com.bot.chat.ws.beans.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parameters implements Serializable
{

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("date-time")
    @Expose
    private List<String> dateTime = new ArrayList<String>();
    @SerializedName("geo-city")
    @Expose
    private String geoCity;
    @SerializedName("unit")
    @Expose
    private String unit;
    private final static long serialVersionUID = 1L;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Parameters withAddress(String address) {
        this.address = address;
        return this;
    }

    public List<String> getDateTime() {
        return dateTime;
    }

    public void setDateTime(List<String> dateTime) {
        this.dateTime = dateTime;
    }

    public Parameters withDateTime(List<String> dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getGeoCity() {
        return geoCity;
    }

    public void setGeoCity(String geoCity) {
        this.geoCity = geoCity;
    }

    public Parameters withGeoCity(String geoCity) {
        this.geoCity = geoCity;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Parameters withUnit(String unit) {
        this.unit = unit;
        return this;
    }

}
