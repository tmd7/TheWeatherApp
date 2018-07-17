package com.tmarat.theweatherapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tmarat.theweatherapp.Contract;
import com.tmarat.theweatherapp.R;
import com.tmarat.theweatherapp.hardware.Sensors;

public class WelcomeScreenFragment extends Fragment implements View.OnClickListener {

  private static final String TAG = WelcomeScreenFragment.class.getSimpleName();
  private Sensors sensors;

  public static WelcomeScreenFragment init() {
    return new WelcomeScreenFragment();
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_welcome_screen, container, false);
    setOnClickListener(view);

    return view;
  }

  @Override public void onPause() {
    super.onPause();
    if (sensors != null) {
      sensors.unregisterSensorListener();
    }
  }

  private void setOnClickListener(View view) {
    view.findViewById(R.id.find_city).setOnClickListener(this);
    view.findViewById(R.id.sensors).setOnClickListener(this);
    view.findViewById(R.id.use_geo).setOnClickListener(this);
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.find_city:
        Log.d(TAG, "onClick: find city");
        startFragment(R.id.main_container, FindCityFragment.init());
        break;

      case R.id.sensors:
        if (getActivity() != null) {
          sensors = new Sensors(getActivity().getApplicationContext());
        }
        if (sensors.doNotHaveAnySensors()) {
          Contract.View cv = (Contract.View) getActivity();
          cv.showToast(R.string.do_not_have_sensors);
        } else {
          startFragment(R.id.main_container, WeatherInfoFragment.init(sensors.getWeatherData()));
        }
        break;

      case R.id.use_geo:
        //startFragment();
        break;
    }
  }

  private void startFragment(int containerViewId, Fragment fragment) {
    if (getActivity() != null) {
      getActivity().getSupportFragmentManager()
          .beginTransaction()
          .replace(containerViewId, fragment)
          .addToBackStack(null)
          .commit();
    } else {
      Log.d(TAG, "startFragment: getActivity == null");
    }
  }
}
