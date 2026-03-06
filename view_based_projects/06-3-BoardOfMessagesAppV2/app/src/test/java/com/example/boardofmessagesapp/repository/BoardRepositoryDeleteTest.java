package com.example.boardofmessagesapp.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BoardRepositoryDeleteTest {

    private CountDownLatch lock;
    private BoardRepository repo = new BoardRepository(true);
    private GeneralResponse res;
    private String resErrorMessage;

    @Before
    public void before() throws Exception {
        repo.reset(); // reset API
        lock = new CountDownLatch(1);
    }

    @Test
    public void testDeleteExistingBoard() throws Exception {
        repo.deleteBoard(1, new BoardRepository.GeneralResponseCallback() {
                    @Override
                    public void onSuccess(GeneralResponse generalResponse) {
                        res = generalResponse;
                        lock.countDown();
                    }

                    @Override
                    public void onError(String errorMessage) {
                        fail();
                        lock.countDown();
                    }
                });

        lock.await(10, TimeUnit.SECONDS);
        assertEquals("Board removed", res.getMessage());
    }

    @Test
    public void testDeleteUnexistingBoard() throws Exception {
        repo.deleteBoard(133333, new BoardRepository.GeneralResponseCallback() {
            @Override
            public void onSuccess(GeneralResponse generalResponse) {
                fail();
                lock.countDown();
            }

            @Override
            public void onError(String errorMessage) {
                resErrorMessage = errorMessage;
                lock.countDown();
            }
        });

        lock.await(10, TimeUnit.SECONDS);
        assertEquals("Not Found", resErrorMessage);
    }

}