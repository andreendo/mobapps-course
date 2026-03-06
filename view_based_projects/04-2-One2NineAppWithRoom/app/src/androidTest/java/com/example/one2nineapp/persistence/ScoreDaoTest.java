package com.example.one2nineapp.persistence;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ScoreDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private ScoreDatabase mDatabase;

    Score SCORE1 = new Score("player 1", 45.77, "some day");
    Score SCORE2 = new Score("player 2", 15.77, "some other day");

    @Before
    public void initDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                ScoreDatabase.class)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }

    @Test
    public void testEmpty() {
        mDatabase.scoreDao()
                .getAllScores()
                .test()
                .assertValue(scores -> scores.size() == 0);
    }

    @Test
    public void testInsertAndSelectInOrder() {
        mDatabase.scoreDao().insertScore(SCORE1).blockingAwait();
        mDatabase.scoreDao().insertScore(SCORE2).blockingAwait();

        mDatabase.scoreDao()
                .getAllScores()
                .test()
                .assertValue(scores -> {
                    return scores.size() == 2 &&
                            scores.get(0).getPlayerName().equals("player 2");
                });
    }

    @Test
    public void testDelete() {
        mDatabase.scoreDao().insertScore(SCORE1).blockingAwait();
        mDatabase.scoreDao().deleteAllScores().blockingGet();
        mDatabase.scoreDao()
                .getAllScores()
                .test()
                .assertValue(scores -> scores.size() == 0);
    }

    @Test
    public void testGetBestScore() {
        mDatabase.scoreDao().insertScore(SCORE1).blockingAwait();
        mDatabase.scoreDao().insertScore(SCORE2).blockingAwait();

        mDatabase.scoreDao()
                .getBestScore()
                .test()
                .assertValue(score -> {
                    return score.getPlayerName().equals("player 2");
                });
    }

    @Test
    public void testGetBestScoreEmpty() {
        mDatabase.scoreDao()
                .getBestScore()
                .test()
                .assertError(error -> {
                    return error != null;
                });
    }
}