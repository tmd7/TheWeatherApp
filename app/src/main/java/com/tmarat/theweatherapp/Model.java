package com.tmarat.theweatherapp;

public class Model implements Contract.Model {
  private String userInput;


  @Override public void getData(String userInput) {
    this.userInput = userInput;
  }
}
