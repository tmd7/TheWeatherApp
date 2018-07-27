package com.tmarat.theweatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.tmarat.theweatherapp.api.WeatherData;
import com.tmarat.theweatherapp.hardware.Sensors;
import com.tmarat.theweatherapp.location.Location;
import com.tmarat.theweatherapp.ui.FindCityFragment;
import com.tmarat.theweatherapp.ui.WeatherInfoFragment;
import com.tmarat.theweatherapp.ui.WelcomeScreenFragment;

public class MainActivity extends AppCompatActivity
    implements ActivityCompat.OnRequestPermissionsResultCallback,
    NavigationView.OnNavigationItemSelectedListener, Contract.View {

  private static final String TAG = MainActivity.class.getSimpleName();

  private Toolbar toolbar;

  private Contract.Presenter presenter;
  private Contract.CoordinatesCallBack coordinatesCallBack;

  private Sensors sensors;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    presenter = new Presenter(this);

    setupToolBar();

    setupNavigationView();

    // TODO: 26.07.2018 if that is a first activity run, start the Welcome fragment
    startFragment(R.id.main_container, WelcomeScreenFragment.init());
  }

  @Override protected void onPause() {
    super.onPause();
    if (sensors != null) {
      sensors.unregisterSensorListener();
    }
  }

  private void startFragment(int containerViewId, Fragment fragment) {
    getSupportFragmentManager()
        .beginTransaction()
        .replace(containerViewId, fragment)
        .commit();
  }

  private void setupNavigationView() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  private void setupToolBar() {
    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {

    switch (item.getItemId()) {

      case R.id.item_find_a_city:
        startFragment(R.id.main_container, FindCityFragment.init());
        break;

      case R.id.item_use_sensors:
        useSensors();
        break;

      case R.id.item_use_geo:
        final Location location = new Location(getApplicationContext(), this,
            new Contract.CoordinatesCallBack() {
              @Override public void onCoordinatesComplete(double latitude, double longitude) {
                presenter.checkGeoCoordinates(latitude, longitude);
              }
            });


        break;
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  private void useSensors() {
    sensors = new Sensors(getApplicationContext());
    if (sensors.doesNotHaveAnySensors()) {
      showToast(R.string.does_not_have_sensors);
    } else {
      startFragment(R.id.main_container, WeatherInfoFragment.init(sensors.getWeatherData()));
    }
  }

  @Override public void showToast(int rsId) {
    Toast.makeText(this, rsId, Toast.LENGTH_SHORT).show();
  }

  @Override public void oButtonClickListener(String userInput) {
    Log.d(TAG, "oButtonClickListener: user input " + userInput);
    presenter.checkInput(userInput);
  }

  @Override public void setWeatherData(WeatherData weatherData) {
    startFragment(R.id.main_container, WeatherInfoFragment.init(weatherData));
  }
}
