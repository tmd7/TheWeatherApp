package com.tmarat.theweatherapp;

import com.tmarat.theweatherapp.api.WeatherData;
import com.tmarat.theweatherapp.api.WeatherRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Contract {

  interface View {
    void showToast(int rsId);

    void oButtonClickListener(String userInput);

    void setWeatherData(WeatherData weatherData);
  }

  interface Presenter {
    void checkInput(String userInput);

    void checkGeoCoordinates(double latitude, double longitude);
  }

  interface Model {
    void getData(String userInput, CallBack callBack);

    void getCoordinates(double latitude, double longitude, CallBack callBack);
  }

  interface OpenWeather {

    @GET("data/2.5/weather") Call<WeatherRequest> getWeatherByCityName(
        @Query("q") String cityName,
        @Query("appid") String keyApi
    );

    @GET("data/2.5/weather") Call<WeatherRequest> getWeatherByCoordinate(

        @Query("lat") String latitude,
        @Query("lon") String longitude,
        @Query("appid") String keyApi
    );
  }

  interface CallBack {
    void onResponse(WeatherData weatherData);

    void onFailure();
  }

  interface CoordinatesCallBack {

    void onCoordinatesComplete(double latitude, double longitude);
  }
}
