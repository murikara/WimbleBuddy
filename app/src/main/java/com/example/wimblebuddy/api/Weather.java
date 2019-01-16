package com.example.wimblebuddy.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class represents an object that contains the encapsulated weather data (see WeatherData class)
 */
public class Weather {

    @SerializedName("main")
    @Expose
    private WeatherData mWeatherData;

    public Weather(WeatherData weatherData) {
        this.mWeatherData = weatherData;
    }

    public WeatherData getWeatherData() {
        return mWeatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.mWeatherData = weatherData;
    }
}
