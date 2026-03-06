package com.example.retrofit1app;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.retrofit1app.service.AnimeQuote;
import com.example.retrofit1app.service.AnimeQuoteRepository;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GetQuotesForCharacterTest {

    private CountDownLatch lock = new CountDownLatch(1);
    private List<AnimeQuote> quotes;

    @Test
    public void testSuccess() throws Exception {
        AnimeQuoteRepository.getCharacterQuotes("saitama", new AnimeQuoteRepository.AnimeQuotesCallback() {
            @Override
            public void onSuccess(List<AnimeQuote> animeQuotes) {
                quotes = animeQuotes;
                lock.countDown();
            }

            @Override
            public void onError(String errorMessage) {
                lock.countDown();
            }
        });

        lock.await(5, TimeUnit.SECONDS);
        assertEquals(10, quotes.size());
    }
}