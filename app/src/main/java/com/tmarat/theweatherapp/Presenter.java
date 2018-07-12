package com.tmarat.theweatherapp;

import com.tmarat.theweatherapp.ui.Contract;

public class Presenter implements Contract.Presenter {
  private Contract.View view;
  private Contract.Model model;

  Presenter(Contract.View view) {
    this.view = view;
    this.model = new Model();
  }

  @Override public void checkInput(String userInput) {
    if (userInput.trim().equals("")) {
      view.showToast(R.string.empty_input);
    } else {
      // TODO: 12.07.2018 pass userInput to model: model.getData(userInput)
      model.getData(userInput);
    }
  }
}
