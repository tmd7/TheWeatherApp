package com.tmarat.theweatherapp.api;

import com.tmarat.theweatherapp.Contract;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {
  private static final String BASE_URL = "http://api.openweathermap.org/";
  //private static final String UNITS = "metric";
  private static final String API = "be4254a9c1592f329d3b479b522e69c3";

  public MyRetrofit(String cityName) {
    //lazy init
  }

  /**
   * Does a request using a city name which an user entered
   * */
  public static Call<WeatherRequest> initRetrofit(String cityName) {

    //Does a request with base url + gson
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient())
        .build();

    return retrofit.create(Contract.OpenWeather.class)
        .getWeatherByCityName(cityName, API);
  }

  /**
   * Does a request using coordinates which takes from Location.class
   * */
  public static Call<WeatherRequest> initRetrofit(double latitude, double longitude) {

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient())
        .build();

    return retrofit.create(Contract.OpenWeather.class)
        .getWeatherByCoordinate(String.valueOf(latitude), String.valueOf(longitude), API);
  }

  /**
   * Logging http request
   * */
  private static OkHttpClient getClient() {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    httpClient.addInterceptor(logging);

    return httpClient.build();
  }
}
