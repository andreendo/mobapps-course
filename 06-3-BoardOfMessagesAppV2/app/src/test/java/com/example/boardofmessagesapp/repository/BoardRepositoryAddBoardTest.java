package com.example.boardofmessagesapp.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BoardRepositoryAddBoardTest {

    private CountDownLatch lock;
    private BoardRepository repo = new BoardRepository(true);
    private Board resBoard;

    @Before
    public void before() throws Exception {
        repo.reset(); // reset API
        lock = new CountDownLatch(1);
    }

    @Test
    public void testAddBoard() throws Exception {
        Board b = new Board();
        b.setName("my board");
        repo.addBoard(b, new BoardRepository.BoardCallback() {
            @Override
            public void onSuccess(Board board) {
                resBoard = board;
                lock.countDown();
            }

            @Override
            public void onError(String errorMessage) {
                fail();
                lock.countDown();
            }
        });

        lock.await(10, TimeUnit.SECONDS);
        assertEquals("my board", resBoard.getName());
    }
}