/**
 * The POJO class
 * */
package com.tmarat.theweatherapp.api;

import com.google.gson.annotations.SerializedName;

public class Main {
  @SerializedName("temp")     private Double  temp;
  @SerializedName("pressure") private Integer pressure;
  @SerializedName("humidity") private Integer humidity;
  @SerializedName("temp_min") private Double  tempMin;
  @SerializedName("temp_max") private Double  tempMax;

  public Double getTemp() {
    return temp;
  }

  public Integer getPressure() {
    return pressure;
  }

  public Integer getHumidity() {
    return humidity;
  }

  public Double getTempMin() {
    return tempMin;
  }

  public Double getTempMax() {
    return tempMax;
  }
}
