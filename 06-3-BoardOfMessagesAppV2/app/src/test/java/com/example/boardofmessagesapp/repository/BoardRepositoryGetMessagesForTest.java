package com.example.boardofmessagesapp.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BoardRepositoryGetMessagesForTest {
    private CountDownLatch lock;
    private BoardRepository repo = new BoardRepository(true);
    private List<Message> resMessages;
    private String error = "";

    @Before
    public void before() throws Exception {
        repo.reset(); // reset API
        lock = new CountDownLatch(1);
    }

    @Test
    public void testGetMessagesForExistingBoard() throws Exception {
        repo.getMessagesFor(1, new BoardRepository.MessagesCallback() {
            @Override
            public void onSuccess(List<Message> messages) {
                resMessages = messages;
                lock.countDown();
            }

            @Override
            public void onError(String errorMessage) {
                error = errorMessage;
                lock.countDown();
            }
        });

        lock.await(10, TimeUnit.SECONDS);
        assertEquals("", error);
        assertEquals(2, resMessages.size());
    }

    @Test
    public void testGetMessagesForUnexistingBoard() throws Exception {
        repo.getMessagesFor(11111, new BoardRepository.MessagesCallback() {
            @Override
            public void onSuccess(List<Message> messages) {
                resMessages = messages;
                lock.countDown();
            }

            @Override
            public void onError(String errorMessage) {
                error = errorMessage;
                lock.countDown();
            }
        });

        lock.await(10, TimeUnit.SECONDS);
        assertEquals("Not Found", error);
    }
}