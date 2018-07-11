package com.tmarat.theweatherapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tmarat.theweatherapp.Contract;
import com.tmarat.theweatherapp.Presenter;
import com.tmarat.theweatherapp.R;

public class WeatherInfoFragment extends Fragment {

  public static WeatherInfoFragment initFragment() {
    return new WeatherInfoFragment();
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_weather_info, container, false);
    return view;
  }
}
