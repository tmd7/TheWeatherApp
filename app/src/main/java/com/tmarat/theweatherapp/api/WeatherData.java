/**
 * The POJO class
 * */
package com.tmarat.theweatherapp.api;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherData implements Parcelable{

  private static final String NO_DATA = "n/d";

  private static WeatherData weatherData;

  private String cityName;
  private String tem;
  private String hum;
  private String press;
  private String wind;
  private String cod;

  public WeatherData() {

  }

  public WeatherData(Parcel in) {
    cityName = in.readString();
    tem = in.readString();
    hum = in.readString();
    press = in.readString();
    wind = in.readString();
    cod = in.readString();
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public void setTem(String tem) {
    this.tem = tem;
  }

  public void setHum(String hum) {
    this.hum = hum;
  }

  public void setPress(String press) {
    this.press = press;
  }

  public void setWind(String wind) {
    this.wind = wind;
  }

  public void setCod(String cod) {
    this.cod = cod;
  }

  public static final Creator<WeatherData> CREATOR = new Creator<WeatherData>() {
    @Override
    public WeatherData createFromParcel(Parcel in) {
      return new WeatherData(in);
    }

    @Override
    public WeatherData[] newArray(int size) {
      return new WeatherData[size];
    }
  };

  public static WeatherData init() {
    if (weatherData == null) {
      weatherData = new WeatherData();
    }
    return weatherData;
  }

  public WeatherData getWeatherData() {
    return weatherData;
  }

  public void setWeatherData(String cityName, String tem, String hum, String press, String wind,
      String cod) {
    this.cityName = cityName == null ? NO_DATA : cityName;
    this.tem = tem == null ? NO_DATA : tem;
    this.hum = hum == null ? NO_DATA : hum;
    this.press = press == null ? NO_DATA : press;
    this.wind = wind == null ? NO_DATA : wind;
    this.cod = cod == null ? NO_DATA : cod;
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

  public String getCod() {
    return cod;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(cityName);
    dest.writeString(tem);
    dest.writeString(hum);
    dest.writeString(press);
    dest.writeString(wind);
    dest.writeString(cod);
  }
}
