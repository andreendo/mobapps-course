package com.example.retrofit1app.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class CloseKeyboard {

    public static void closeKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
