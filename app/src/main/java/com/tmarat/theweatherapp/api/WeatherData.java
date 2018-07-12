package com.tmarat.theweatherapp.api;

public class WeatherData {

  private static final String NO_DATA = "n/d";

  private static WeatherData weatherData;

  private String cityName;
  private String tem;
  private String hum;
  private String press;
  private String wind;

  private WeatherData() {
    //Uses lazy init
  }

  public static WeatherData init() {
    if (weatherData == null) {
      weatherData = new WeatherData();
    }
    return weatherData;
  }

  public void setWeatherData(String cityName, String tem, String hum, String press, String wind) {
    this.cityName = cityName == null ? NO_DATA : cityName;
    this.tem = tem == null ? NO_DATA : tem;
    this.hum = hum == null ? NO_DATA : hum;
    this.press = press == null ? NO_DATA : press;
    this.wind = wind == null ? NO_DATA : wind;
  }

  public String getCityName() {
    return cityName;
  }

  public String getTem() {
    return tem;
  }

  public String getHum() {
    return hum;
  }

  public String getPress() {
    return press;
  }

  public String getWind() {
    return wind;
  }
}
