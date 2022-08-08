package com.example.roomapp1.persistence.dao;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.roomapp1.persistence.IssuesDatabase;
import com.example.roomapp1.persistence.model.Category;
import com.example.roomapp1.persistence.model.Issue;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CategoryDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private IssuesDatabase mDatabase;

    Category CAT1 = new Category(1, "High");
    Category CAT2 = new Category(2, "Medium");
    Category CAT3 = new Category(3, "Low");

    Issue ISSUE1 = new Issue("issue1", "desc1", CAT2.categoryId);
    Issue ISSUE2 = new Issue("issue2", "desc2", CAT1.categoryId);
    Issue ISSUE3 = new Issue("issue3", "desc3", CAT2.categoryId);

    @Before
    public void initDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                IssuesDatabase.class)
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
        mDatabase.categoryDao()
                .getAll()
                .test()
                .assertValue(categories -> categories.size() == 0);
    }

    @Test
    public void testInsertAndSelectInOrder() {
        mDatabase.categoryDao().insert(CAT1).blockingAwait();
        mDatabase.categoryDao().insert(CAT2).blockingAwait();
        mDatabase.categoryDao().insert(CAT3).blockingAwait();

        mDatabase.categoryDao()
                .getAll()
                .test()
                .assertValue(categories -> {
                    return categories.size() == 3 &&
                            categories.get(1).name.equals("Medium");
                });
    }

    @Test
    public void testCategoryWithIssues() {
        mDatabase.categoryDao().insert(CAT1).blockingAwait();
        mDatabase.categoryDao().insert(CAT2).blockingAwait();
        mDatabase.categoryDao().insert(CAT3).blockingAwait();
        mDatabase.issueDao().insert(ISSUE1).blockingAwait();
        mDatabase.issueDao().insert(ISSUE2).blockingAwait();
        mDatabase.issueDao().insert(ISSUE3).blockingAwait();

        mDatabase.categoryDao()
                .getCategoryWithIssues(CAT2.categoryId)
                .test()
                .assertValue(c -> {
                    return c.category.name.equals("Medium") &&
                            c.issues.size() == 2;
                });

        mDatabase.categoryDao()
                .getCategoryWithIssues(CAT1.categoryId)
                .test()
                .assertValue(c -> {
                    return c.category.name.equals("High") &&
                            c.issues.size() == 1 &&
                            c.issues.get(0).name.equals("issue2");
                });
    }

}