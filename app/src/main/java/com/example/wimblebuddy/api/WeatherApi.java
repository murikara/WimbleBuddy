package com.example.wimblebuddy.api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    String BASE_URL = "http://api.openweathermap.org/";
    String UNIT_METRIC = "metric";
    String APIKEY = "0c35fa739b50f4235ed7415277fd5367";

    //Create new retrofit instance
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    //This call is used to retrieve weather data from the specified URL
    //The required parameters are used for queries, to ensure that the correct data is returned on performing the API call
    //Parameters are as follows: location (for example a city), unit (measure unit used for temperature, for example celsius or fahrenheit), key (API key)
    @GET("/data/2.5/weather")
    Call<Weather> getWeather(@Query("q") String location,
                                   @Query("units") String unit,
                                   @Query("APPID") String key);
}
