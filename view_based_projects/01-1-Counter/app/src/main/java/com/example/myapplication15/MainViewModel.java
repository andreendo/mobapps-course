package com.example.myapplication15;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Integer> counter = new MutableLiveData<>(0);

    public LiveData<Integer> getCounter() {
        return counter;
    }

    public void incrementCounter() {
        int newValue = counter.getValue() + 1;
        counter.setValue(newValue);
    }
}
