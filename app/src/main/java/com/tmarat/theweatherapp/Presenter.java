package com.tmarat.theweatherapp;

public class Presenter implements Contract.Presenter {

  private final Contract.View view;
  private final Contract.Model model;

  public Presenter(Contract.View view) {
    this.view = view;
    this.model = new Model();
  }
}
