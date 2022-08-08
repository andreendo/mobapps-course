package com.example.boardofmessagesapp.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BoardRepositoryPostMessageTest {
    private CountDownLatch lock;
    private BoardRepository repo = new BoardRepository(true);
    private GeneralResponse res;

    @Before
    public void before() throws Exception {
        repo.reset(); // reset API
        lock = new CountDownLatch(1);
    }

    @Test
    public void testPostMessageToBoard() throws Exception {
        Message msg = new Message();
        msg.setFrom("Chuck");
        msg.setTo("Norris");
        msg.setText("Hi hi");
        repo.postMessage(1, msg, new BoardRepository.GeneralResponseCallback() {
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
        assertEquals("Message added to board board_test1", res.getMessage());
    }

}