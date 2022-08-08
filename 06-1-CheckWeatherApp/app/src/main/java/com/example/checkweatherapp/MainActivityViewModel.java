package com.example.checkweatherapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.checkweatherapp.weatherService.WeatherRepository;

public class MainActivityViewModel extends ViewModel {
    private final MutableLiveData<String> city = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Double> temperature = new MutableLiveData<>();

    public void getWeatherFor(boolean isConnected, String cityName) {
        city.setValue("");
        if (!isConnected) {
            errorMessage.setValue("No Internet connection. Try again later");
            return;
        }

        WeatherRepository.getTemperatureFor(cityName, new WeatherRepository.WeatherCallback() {
            @Override
            public void onSuccess(double temp) {
                city.setValue(cityName);
                temperature.setValue(temp);
            }

            @Override
            public void onError(String msg) {
                errorMessage.setValue(msg);
            }
        });
    }

    public MutableLiveData<String> getCity() {
        return city;
    }

    public MutableLiveData<Double> getTemperature() {
        return temperature;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
