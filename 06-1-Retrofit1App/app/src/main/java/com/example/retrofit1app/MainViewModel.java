package com.example.retrofit1app;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit1app.service.AnimeQuote;
import com.example.retrofit1app.service.AnimeQuoteRepository;


public class MainViewModel extends ViewModel {
    final MutableLiveData<Boolean> showLoading = new MutableLiveData<>(false);
    final MutableLiveData<String> quote = new MutableLiveData<>();

    public MutableLiveData<Boolean> getShowLoading() {
        return showLoading;
    }

    public MutableLiveData<String> getQuote() {
        return quote;
    }

    public void newQuote() {
        quote.setValue("");
        showLoading.setValue(true);
        AnimeQuoteRepository.getQuote(new AnimeQuoteRepository.AnimeQuoteCallback() {
            @Override
            public void onSuccess(AnimeQuote animeQuote) {
                showLoading.setValue(false);
                quote.setValue(animeQuote.getQuote());
            }

            @Override
            public void onError(String errorMessage) { }
        });
    }
}
