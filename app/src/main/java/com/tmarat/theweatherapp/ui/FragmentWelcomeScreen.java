package com.tmarat.theweatherapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tmarat.theweatherapp.R;

public class FragmentWelcomeScreen extends Fragment implements View.OnClickListener {

  private static final String TAG = FragmentWelcomeScreen.class.getSimpleName();

  public static Fragment init() {
    return new FragmentWelcomeScreen();
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_welcome_screen, container, false);
    return view;
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.find_city :
        //startFragment(R.id.main_container, FragmentFindCity.init());
        break;

      case R.id.sensors :
        //startFragment();
        break;

      case R.id.use_geo :
        //startFragment();
        break;
    }
  }

  private void startFragment(int containerViewId, Fragment fragment) {
    if (getActivity() != null) {
      getActivity().getSupportFragmentManager()
          .beginTransaction()
          .replace(containerViewId, fragment)
          .addToBackStack("")
          .commit();
    } else {
      Log.d(TAG, "startFragment: getActivity == null");
    }
  }
}
