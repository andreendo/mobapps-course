package com.example.boardofmessagesapp.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BoardRepositoryResetTest {

    private CountDownLatch lock;
    private BoardRepository repo = new BoardRepository(true);
    private List<Board> resBoards;

    @Before
    public void before() throws Exception {
        repo.reset(); // reset API
    }

    @Test
    public void testResetAPI() throws Exception {
        addBoard();
        addBoard();
        lock = new CountDownLatch(1);
        repo.getBoards(new BoardRepository.BoardsCallback() {
            @Override
            public void onSuccess(List<Board> boards) {
                resBoards = boards;
                lock.countDown();
            }

            @Override
            public void onError(String errorMessage) {
                fail();
                lock.countDown();
            }
        });

        lock.await(10, TimeUnit.SECONDS);
        assertEquals(4, resBoards.size());

        repo.reset();

        lock = new CountDownLatch(1);
        repo.getBoards(new BoardRepository.BoardsCallback() {
            @Override
            public void onSuccess(List<Board> boards) {
                resBoards = boards;
                lock.countDown();
            }

            @Override
            public void onError(String errorMessage) {
                fail();
                lock.countDown();
            }
        });

        lock.await(10, TimeUnit.SECONDS);
        assertEquals(2, resBoards.size());
    }

    private void addBoard() throws Exception {
        lock = new CountDownLatch(1);
        Board b = new Board();
        b.setName("my board");
        repo.addBoard(b, new BoardRepository.BoardCallback() {
            @Override
            public void onSuccess(Board board) {
                lock.countDown();
            }

            @Override
            public void onError(String errorMessage) {
                fail();
                lock.countDown();
            }
        });

        lock.await(10, TimeUnit.SECONDS);
    }
}