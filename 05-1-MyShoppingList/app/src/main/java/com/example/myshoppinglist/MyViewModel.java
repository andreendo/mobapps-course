package com.example.myshoppinglist;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<String>> shoppingList;

    public LiveData<List<String>> getShoppingList() {
        if (shoppingList == null) {
            shoppingList = new MutableLiveData<>();
            loadShoppingList();
        }

        return shoppingList;
    }

    private void loadShoppingList() {
        // it is associated with the main thread
        Handler handler = new Handler(Looper.getMainLooper());
        // run in a after the delay
        handler.postDelayed(() -> {
            // begin - runnable block
            // it simulates a call that takes some time to return
            List<String> listSample = Arrays.asList(
                    "Bread", "Bananas", "Peanut Butter", "Eggs", "Chicken breasts"
            );
            long seed = System.nanoTime();
            Collections.shuffle(listSample, new Random(seed));

            //notify that the live data changed
            shoppingList.setValue(listSample);
            // end - runnable block
        }, 5000);
    }
}
