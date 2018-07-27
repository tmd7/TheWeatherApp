/**
 * The class uses hardware sensors
 * */
package com.tmarat.theweatherapp.hardware;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.tmarat.theweatherapp.api.WeatherData;

public class Sensors {
  private SensorManager sensorManager;

  private WeatherData weatherData;

  private SensorEventListener listenerTem;
  private SensorEventListener listenerHum;
  private SensorEventListener listenerPress;

  /**
   * The constructor uses a weak reference with context
   * */
  public Sensors(Context context) {
    this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    weatherData = new WeatherData();
    setSensorListeners();
    registerSensorListener();
  }

  public WeatherData getWeatherData() {
    return weatherData;
  }

  private Sensor getSensorTem() {
    return sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
  }

  private Sensor getSensorHum() {
    return sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
  }

  private Sensor getSensorPress() {
    return sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
  }

  /**
   * Temperature, humidity and pressure listeners
   * */
  private void setSensorListeners() {
    if (getSensorTem() != null) {
      listenerTem = new SensorEventListener() {
        @Override public void onSensorChanged(SensorEvent event) {
          if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            weatherData.setTem(String.valueOf(event.values[0]));
          }
        }
        @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
      };
    }

    if (getSensorHum() != null) {
      listenerHum = new SensorEventListener() {
        @Override public void onSensorChanged(SensorEvent event) {
          if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            weatherData.setHum(String.valueOf(event.values[0]));
          }
        }
        @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
      };
    }

    if (getSensorPress() != null) {
      listenerPress = new SensorEventListener() {
        @Override public void onSensorChanged(SensorEvent event) {
          if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            weatherData.setPress(String.valueOf(event.values[0]));
          }
        }

        @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
      };
    }
  }

  /**
   * This method calls into constructor
   * */
  private void registerSensorListener() {
    if (getSensorTem() != null) {
      sensorManager.registerListener(listenerTem, getSensorTem(), SensorManager.SENSOR_DELAY_NORMAL);
    }
    if (getSensorHum() != null) {
      sensorManager.registerListener(listenerHum, getSensorHum(), SensorManager.SENSOR_DELAY_NORMAL);
    }
    if (getSensorPress() != null) {
      sensorManager.registerListener(listenerPress, getSensorPress(), SensorManager.SENSOR_DELAY_NORMAL);
    }
  }

  /**
   * This method calls into MainActivity into method onPause()
   * */
  public void unregisterSensorListener() {
    if (getSensorTem() != null) {
      sensorManager.unregisterListener(listenerTem,getSensorTem());
    }
    if (getSensorHum() != null) {
      sensorManager.unregisterListener(listenerHum,getSensorHum());
    }
    if (getSensorPress() != null) {
      sensorManager.unregisterListener(listenerPress,getSensorPress());
    }
  }

  /**
   * Checks if the device has sensors
   * */
  public boolean doesNotHaveAnySensors() {
    return getSensorTem() == null || getSensorHum() == null || getSensorPress() == null;
  }
}
