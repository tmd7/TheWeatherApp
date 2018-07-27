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
import com.tmarat.theweatherapp.api.WeatherData;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WeatherInfoFragment extends Fragment {

  private static final String WEATHER_INFO = "weatherInfo";
  private TextView textViewCityName;
  private TextView textViewDate;
  private TextView textViewTem;
  private TextView textViewPress;
  private TextView textViewHum;
  private TextView textViewWind;

  public static WeatherInfoFragment init(WeatherData weatherData) {

    WeatherInfoFragment fragment = new WeatherInfoFragment();
    Bundle args = new Bundle();
    args.putParcelable(WEATHER_INFO, weatherData);
    fragment.setArguments(args);
    return fragment;
  }

  public WeatherData getWeatherData() {
    return getArguments().getParcelable(WEATHER_INFO);
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_weather_info, container, false);
    setupUI(view);
    setWeatherData();

    return view;
  }

  private void setWeatherData() {

    WeatherData weatherData = getWeatherData();
    textViewCityName.setText(weatherData.getCityName());
    textViewDate.setText(getCurrentDate());
    textViewTem.setText(weatherData.getTem());

    String press = getString(R.string.pressure, weatherData.getPress());
    String hum = getString(R.string.humidity, weatherData.getHum());
    String wind = getString(R.string.wind, weatherData.getWind());
    textViewPress.setText(press);
    textViewHum.setText(hum);
    textViewWind.setText(wind);
  }

  private void setupUI(View view) {

    textViewCityName = view.findViewById(R.id.header_city_name);
    textViewDate = view.findViewById(R.id.header_date);
    textViewTem = view.findViewById(R.id.tem);
    textViewPress = view.findViewById(R.id.press);
    textViewHum = view.findViewById(R.id.hum);
    textViewWind = view.findViewById(R.id.wind);
  }

  public String getCurrentDate() {

    Calendar calendar = Calendar.getInstance();
    return new SimpleDateFormat("yyyy-MM-dd", Locale.UK).format(calendar.getTime());
  }
}
