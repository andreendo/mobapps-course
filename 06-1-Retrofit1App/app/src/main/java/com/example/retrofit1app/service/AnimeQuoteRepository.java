package com.example.retrofit1app.service;

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

    // set a generic callback interface
    public interface AnimeQuoteCallback {
        public void onSuccess(AnimeQuote animeQuote);

        public void onError(String errorMessage);
    }
}
