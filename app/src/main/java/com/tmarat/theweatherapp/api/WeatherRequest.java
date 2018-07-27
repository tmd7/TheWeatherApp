/**
 * The POJO class, keeps weather data after a client request
 * */
package com.tmarat.theweatherapp.api;

import com.google.gson.annotations.SerializedName;

public class WeatherRequest {
  @SerializedName("main") private Main   main;
  @SerializedName("name") private String name;
  @SerializedName("cod")  private String cod;


  public Main getMain() {
    return main;
  }

  public String getName() {
    return name;
  }

  public String getCod() {
    return cod;
  }
}
