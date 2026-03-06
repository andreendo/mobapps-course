package com.example.retrofit1app;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit1app.service.AnimeQuote;
import com.example.retrofit1app.service.AnimeQuoteRepository;

import java.util.ArrayList;
import java.util.List;


public class MainViewModel extends ViewModel {
    final MutableLiveData<Boolean> showLoading = new MutableLiveData<>(false);
    final MutableLiveData<String> errorMessage = new MutableLiveData<>("");
    final MutableLiveData<String> quote = new MutableLiveData<>();
    final MutableLiveData<List<AnimeQuote>> quotes = new MutableLiveData<>();

    public MutableLiveData<Boolean> getShowLoading() {
        return showLoading;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<String> getQuote() {
        return quote;
    }

    public MutableLiveData<List<AnimeQuote>> getQuotes() {
        return quotes;
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

    public void getQuotesFor(String characterName) {
        showLoading.setValue(true);
        AnimeQuoteRepository.getCharacterQuotes(characterName, new AnimeQuoteRepository.AnimeQuotesCallback() {
            @Override
            public void onSuccess(List<AnimeQuote> animeQuotes) {
                showLoading.setValue(false);
                if (animeQuotes != null)
                    quotes.setValue(animeQuotes);
                else
                    quotes.setValue(new ArrayList<>());
            }

            @Override
            public void onError(String error) {
                showLoading.setValue(false);
                quotes.setValue(new ArrayList<>());
                errorMessage.setValue(error + "\nTry again later!");
            }
        });
    }
}
