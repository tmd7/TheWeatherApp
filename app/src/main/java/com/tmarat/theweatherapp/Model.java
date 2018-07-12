package com.tmarat.theweatherapp;

import com.tmarat.theweatherapp.ui.Contract;

public class Model implements Contract.Model {
  private String userInput;


  @Override public void getData(String userInput) {
    this.userInput = userInput;
  }
}
