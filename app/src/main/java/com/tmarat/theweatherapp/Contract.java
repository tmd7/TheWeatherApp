package com.tmarat.theweatherapp;

import com.tmarat.theweatherapp.api.WeatherRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Contract {

  interface View{
    void showToast(int rsId);
    void oButtonClickListener(String userInput);
  }

  interface Presenter{
    void checkInput(String s);
  }

  interface Model{
    void getData(String userInput);
  }

  interface OpenWeather {
    @GET("data/2.5/weather")
    Call<WeatherRequest> loadWeather(
        @Query("q") String cityName,
        @Query("appid") String keyApi);
  }
}
