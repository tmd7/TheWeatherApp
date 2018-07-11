package com.tmarat.theweatherapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tmarat.theweatherapp.R;

public class FindCityFragment extends Fragment {

  private TextInputEditText input;
  private StringBuilder builder;

  public static FindCityFragment init() {
    return new FindCityFragment();
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    builder = new StringBuilder();
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_find_city, container, false);
    input = view.findViewById(R.id.input_city_name);
    setButtonOnclickListener(view);
    return view;
  }

  private void setButtonOnclickListener(View view) {
    view.findViewById(R.id.bt_send).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        builder.append(input.getText().toString().trim());
      }
    });
  }
}
