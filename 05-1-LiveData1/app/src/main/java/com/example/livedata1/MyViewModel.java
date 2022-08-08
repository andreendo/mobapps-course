package com.example.livedata1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private final MutableLiveData<Integer> counter = new MutableLiveData<>(0);
    private int last = 0;

    public void twoPoints() {
        add(2);
        last = 2;
    }

    public void threePoints() {
        add(3);
        last = 3;
    }

    public void freeThrow() {
        add(1);
        last = 1;
    }

    public void rollback() {
        if (last != 0) {
            add(-last);
            last = 0;
        }
    }

    private void add(int value) {
        int inc = counter.getValue() + value;
        counter.setValue(inc);
    }

    public void restart() {
        counter.setValue(0);
    }

    public LiveData<Integer> getCounter() {
        return counter;
    }
}
