package com.tmarat.theweatherapp.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import static android.content.Context.LOCATION_SERVICE;

public class Location implements ActivityCompat.OnRequestPermissionsResultCallback {

  private static final int PERMISSION_REQUEST_CODE = 10;
  private static final String TAG = Location.class.getSimpleName();

  private int accessFineLocation;
  private int accessCoarseLocation;
  private boolean permissionCallPhone;
  private Object systemService;

  private String latitude;
  private String longitude;

  /**
   * Constructor(context, activity)
   */
  public Location(Context context, Activity activity) {
    this.accessCoarseLocation = ActivityCompat
        .checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);

    this.accessFineLocation = ActivityCompat
        .checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);

    this.permissionCallPhone = ActivityCompat
        .shouldShowRequestPermissionRationale(activity, Manifest.permission.CALL_PHONE);

    this.systemService = activity.getSystemService(LOCATION_SERVICE);

    checkPermission(activity);
  }

  public String getLatitude() {
    return latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  private void checkPermission(Activity activity) {

    if (accessCoarseLocation == PackageManager.PERMISSION_GRANTED
        || accessFineLocation == PackageManager.PERMISSION_GRANTED) {

      requestLocation();

    } else {
      requestLocationPermissions(activity);
    }
  }

  private void requestLocationPermissions(Activity activity) {
    if (!permissionCallPhone) {

      ActivityCompat.requestPermissions(activity,
          new String[] {
              Manifest.permission.ACCESS_COARSE_LOCATION,
              Manifest.permission.ACCESS_FINE_LOCATION
          },
          PERMISSION_REQUEST_CODE);
    }
  }

  @SuppressLint("MissingPermission")
  private void requestLocation() {
    if (accessCoarseLocation != PackageManager.PERMISSION_GRANTED
        && accessFineLocation != PackageManager.PERMISSION_GRANTED) {

      return;
    }

    LocationManager locationManager = (LocationManager) systemService;
    Criteria criteria = new Criteria();
    criteria.setAccuracy(Criteria.ACCURACY_COARSE);

    String provider = locationManager.getBestProvider(criteria, true);

    if (provider != null) {

      locationManager.requestLocationUpdates(provider, 10_000, 10, new LocationListener() {
        @Override public void onLocationChanged(android.location.Location location) {
          latitude = Double.toString(location.getLatitude());
          longitude = Double.toString(location.getLongitude());

          String result = String.format("latitude = %s, longitude = %s", latitude, longitude);
          Log.d(TAG, "onLocationChanged: " + result);
        }

        @Override public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        @Override public void onProviderEnabled(String provider) {
        }
        @Override public void onProviderDisabled(String provider) {
        }
      });
    }
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    if (requestCode == PERMISSION_REQUEST_CODE) {

      if (grantResults.length == 2
          && (grantResults[0] == PackageManager.PERMISSION_GRANTED
          || grantResults[1] == PackageManager.PERMISSION_GRANTED)) {

        requestLocation();
      }
    }
  }
}
