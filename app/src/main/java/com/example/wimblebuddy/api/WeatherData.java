package com.example.wimblebuddy.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class represents an object that contains weather related data
 * This data is retrieved using an API
 */
public class WeatherData {

    @SerializedName("temp")
    @Expose
    private Double mTemp;
    @SerializedName("pressure")
    @Expose
    private Integer mPressure;
    @SerializedName("humidity")
    @Expose
    private Integer mHumidity;
    @SerializedName("temp_min")
    @Expose
    private Double mTempMin;
    @SerializedName("temp_max")
    @Expose
    private Double mTempMax;

    public WeatherData(Double temp, Integer pressure, Integer humidity, Double tempMin, Double tempMax) {
        this.mTemp = temp;
        this.mPressure = pressure;
        this.mHumidity = humidity;
        this.mTempMin = tempMin;
        this.mTempMax = tempMax;
    }

    public Double getTemp() {
        return mTemp;
    }

    public void setTemp(Double temp) {
        this.mTemp = temp;
    }

    public Integer getPressure() {
        return mPressure;
    }

    public void setPressure(Integer pressure) {
        this.mPressure = pressure;
    }

    public Integer getHumidity() {
        return mHumidity;
    }

    public void setHumidity(Integer humidity) {
        this.mHumidity = humidity;
    }

    public Double getTempMin() {
        return mTempMin;
    }

    public void setTempMin(Double tempMin) {
        this.mTempMin = tempMin;
    }

    public Double getTempMax() {
        return mTempMax;
    }

    public void setTempMax(Double tempMax) {
        this.mTempMax = tempMax;
    }
}
