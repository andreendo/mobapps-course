package com.example.roomapp1.persistence.dao;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.roomapp1.persistence.IssuesDatabase;
import com.example.roomapp1.persistence.model.Category;
import com.example.roomapp1.persistence.model.Issue;
import com.example.roomapp1.persistence.model.IssueAndCategory;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class IssueDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private IssuesDatabase mDatabase;

    Category CAT1 = new Category(1, "High");
    Category CAT2 = new Category(2, "Medium");
    Category CAT3 = new Category(3, "Low");

    Issue ISSUE1 = new Issue("issue1", "desc1", CAT1.categoryId);
    Issue ISSUE2 = new Issue("issue2", "desc2", CAT2.categoryId);

    @Before
    public void initDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                IssuesDatabase.class)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build();

        mDatabase.categoryDao().insert(CAT1).blockingAwait();
        mDatabase.categoryDao().insert(CAT2).blockingAwait();
        mDatabase.categoryDao().insert(CAT3).blockingAwait();
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }

    @Test
    public void testEmpty() {
        mDatabase.issueDao()
                .getAll()
                .test()
                .assertValue(categories -> categories.size() == 0);
    }

    @Test
    public void testInsertAndSelect() {
        mDatabase.issueDao().insert(ISSUE1).blockingAwait();
        mDatabase.issueDao().insert(ISSUE2).blockingAwait();

        mDatabase.issueDao()
                .getAll()
                .test()
                .assertValue(issues -> {
                    return issues.size() == 2 &&
                            issues.get(0).name.equals("issue1") &&
                            issues.get(0).description.equals("desc1") &&
                            issues.get(0).categoryId == CAT1.categoryId;
                });
    }

    @Test
    public void testIssueAndCategory() {
        mDatabase.issueDao().insert(ISSUE1).blockingAwait();

        mDatabase.issueDao()
                .getIssuesWithCategory()
                .test()
                .assertValue(issues -> {
                    IssueAndCategory i1 = issues.get(0);
                    return issues.size() == 1 &&
                            i1.issue.name.equals("issue1") &&
                            i1.issue.description.equals("desc1") &&
                            i1.category.categoryId == 1 &&
                            i1.category.name.equals("High");
                });
    }
}