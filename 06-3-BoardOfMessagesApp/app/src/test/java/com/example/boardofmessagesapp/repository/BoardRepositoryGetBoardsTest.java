package com.example.boardofmessagesapp.repository;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BoardRepositoryGetBoardsTest {
    private CountDownLatch lock;
    private BoardRepository repo = new BoardRepository(true);
    private List<Board> resBoards;
    private Board resBoard;

    @Before
    public void before() throws Exception {
        repo.reset(); // reset API
        lock = new CountDownLatch(1);
    }

    @Test
    public void testGetBoards() throws Exception {
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
        assertEquals("board_test1", resBoards.get(0).getName());
        assertEquals(2, resBoards.get(0).getMessages().size());
        assertEquals("board_test2", resBoards.get(1).getName());
        assertEquals(1, resBoards.get(1).getMessages().size());
    }

    @Test
    public void testGetBoard() throws Exception {
        repo.getBoard(1, new BoardRepository.BoardCallback() {
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
        assertEquals(new Integer(1), resBoard.getId());
    }
}