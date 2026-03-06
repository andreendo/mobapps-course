package com.example.retrofit1app.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeQuoteRepository {
    public static void getQuote(AnimeQuoteCallback callback) {
        AnimeQuoteInterface client = AnimeQuoteAPIClient.getClient().create(AnimeQuoteInterface.class);
        Call<AnimeQuote> call = client.getRandomQuote();
        call.enqueue(new Callback<AnimeQuote>() {
            @Override
            public void onResponse(Call<AnimeQuote> call, Response<AnimeQuote> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<AnimeQuote> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public static void getCharacterQuotes(String name, AnimeQuotesCallback cb) {
        AnimeQuoteInterface client = AnimeQuoteAPIClient.getClient()
                .create(AnimeQuoteInterface.class);

        Call<List<AnimeQuote>> call = client.getQuotesFor(name);
        call.enqueue(new Callback<List<AnimeQuote>>() {
            @Override
            public void onResponse(Call<List<AnimeQuote>> call, Response<List<AnimeQuote>> response) {
                cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<AnimeQuote>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    // set a generic callback interface
    public interface AnimeQuoteCallback {
        public void onSuccess(AnimeQuote animeQuote);

        public void onError(String errorMessage);
    }

    public interface AnimeQuotesCallback {
        public void onSuccess(List<AnimeQuote> animeQuotes);

        public void onError(String errorMessage);
    }
}
