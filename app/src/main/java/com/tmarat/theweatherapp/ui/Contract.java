package com.tmarat.theweatherapp.ui;

public interface Contract {

  interface View{
    void showToast(int rsId);
    void oButtonClickListener(String userInput);
  }

  interface Presenter{
    void checkInput(String s);
  }

  interface Model{
    void getData(String userInput);
  }
}
