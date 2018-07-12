package com.tmarat.theweatherapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tmarat.theweatherapp.R;

public class WeatherInfoFragment extends Fragment {

  private TextView textViewCityname;
  private TextView textViewDate;
  private TextView textViewTem;
  private TextView textViewPress;
  private TextView textViewHum;
  private TextView textViewWind;

  public static WeatherInfoFragment init() {
    return new WeatherInfoFragment();
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_weather_info, container, false);
    setupUI(view);
    return view;
  }

  private void setupUI(View view) {
    textViewCityname = view.findViewById(R.id.header_city_name);
    textViewDate = view.findViewById(R.id.header_date);
    textViewTem = view.findViewById(R.id.tem);
    textViewPress = view.findViewById(R.id.press);
    textViewHum = view.findViewById(R.id.hum);
    textViewWind = view.findViewById(R.id.wind);
  }
}
