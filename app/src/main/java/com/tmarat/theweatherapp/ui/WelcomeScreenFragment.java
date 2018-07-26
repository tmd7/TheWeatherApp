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

public class WelcomeScreenFragment extends Fragment {

  public static WelcomeScreenFragment init() {
    return new WelcomeScreenFragment();
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_welcome_screen, container, false);
  }
}
