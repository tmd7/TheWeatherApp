package com.tmarat.theweatherapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.tmarat.theweatherapp.api.WeatherData;

public class Presenter implements Contract.Presenter {
  private static final String TAG = Presenter.class.getSimpleName();
  private Contract.View view;
  private Contract.Model model;

  Presenter(Contract.View view) {
    this.view = view;
    this.model = new Model();
  }

  @Override public void checkInput(String userInput) {
    if (userInput == null) {

      view.showToast(R.string.empty_input);

    } else {

      model.getData(userInput, new Contract.CallBack() {

        @Override public void onResponse(@NonNull WeatherData weatherData) {
          //CallBack : return weatherData object from server
          Log.d(TAG, "onResponse: " + weatherData.getCityName());

          //Passes data to activity
          view.getWeatherData(weatherData);
        }

        @Override public void onFailure() {
          //Callback from model : City don't found or server isn't responding
          view.showToast(R.string.server_is_not_responding);
        }
      });
    }
  }
}
