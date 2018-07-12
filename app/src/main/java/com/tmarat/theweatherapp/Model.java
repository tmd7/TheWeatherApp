package com.tmarat.theweatherapp;

import android.util.Log;
import com.tmarat.theweatherapp.api.MyRetrofit;
import com.tmarat.theweatherapp.api.WeatherData;
import com.tmarat.theweatherapp.api.WeatherRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.Model {

  private static final String TAG = Model.class.getSimpleName();

  @Override public void getData(String userInput,
      final Contract.CallBack callBack) {

    //userInput is not empty, do a request
    MyRetrofit.initRetrofit(userInput).enqueue(new Callback<WeatherRequest>() {
      @Override
      public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {

        if (response.body() != null) {

          Log.d(TAG, "onResponse: body != null");
          wrapResponse(response.body());
          callBack.onResponse(WeatherData.init().getWeatherData());

        } else {

          callBack.onFailure();
        }
      }

      @Override public void onFailure(Call<WeatherRequest> call, Throwable t) {
        Log.d(TAG, "onFailure: ");
        callBack.onFailure();
      }
    });
  }

  private void wrapResponse(WeatherRequest body) {
    //wrap response.body to POJO WeatherData.class
    WeatherData.init().setWeatherData(
        body.getName(),
        String.valueOf(body.getMain().getTemp()),
        String.valueOf(body.getMain().getHumidity()),
        String.valueOf(body.getMain().getPressure()),
        "n/d",
        body.getCod()
    );

  }

}
