package com.example.roomapp1.persistence.dao;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.roomapp1.persistence.IssuesDatabase;
import com.example.roomapp1.persistence.model.Category;
import com.example.roomapp1.persistence.model.Issue;
import com.example.roomapp1.persistence.model.IssueAndCategory;
import com.example.roomapp1.persistence.model.Project;
import com.example.roomapp1.persistence.model.ProjectIssueCrossRef;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ProjectDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private IssuesDatabase mDatabase;

    Category CAT1 = new Category(1, "High");
    Category CAT2 = new Category(2, "Medium");

    Issue ISSUE1 = new Issue(1, "issue1", "desc1", CAT1.categoryId);
    Issue ISSUE2 = new Issue(2, "issue2", "desc2", CAT2.categoryId);

    Project PROJ1 = new Project(10, "proj1");

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
        mDatabase.projectDao()
                .getAll()
                .test()
                .assertValue(categories -> categories.size() == 0);
    }

    @Test
    public void testInsertCrossRef() {
        ProjectIssueCrossRef c = new ProjectIssueCrossRef(1, 2);
        mDatabase.projectDao()
                .insertIssueCrossRef(c).blockingAwait();

        mDatabase.projectDao()
                .getProjectIssueCrossRefs()
                .test()
                .assertValue( projectIssueCrossRefs -> {
                   return projectIssueCrossRefs.size() == 1;
                });
    }

    @Test
    public void testGetProjectsWithIssues() {
        prepareData();
        mDatabase.projectDao()
                .getProjectsWithIssues()
                .test()
                .assertValue( projects -> {
                    return projects.size() == 1 &&
                            projects.get(0).issues.size() == 2;
                });
    }

    @Test
    public void testGetProjectWithIssues() {
        prepareData();
        mDatabase.projectDao()
                .getProjectWithIssues(PROJ1.projectId)
                .test()
                .assertValue( p -> {
                    return p.project.projectId == PROJ1.projectId &&
                            p.issues.size() == 2;
                });
    }

    private void prepareData() {
        mDatabase.issueDao().insert(ISSUE1).blockingAwait();
        mDatabase.issueDao().insert(ISSUE2).blockingAwait();
        mDatabase.projectDao().insert(PROJ1).blockingAwait();
        mDatabase.projectDao()
                .insertIssueCrossRef(new ProjectIssueCrossRef(
                        PROJ1.projectId, ISSUE1.issueId
                )).blockingAwait();
        mDatabase.projectDao()
                .insertIssueCrossRef(new ProjectIssueCrossRef(
                        PROJ1.projectId, ISSUE2.issueId
                )).blockingAwait();
    }
}