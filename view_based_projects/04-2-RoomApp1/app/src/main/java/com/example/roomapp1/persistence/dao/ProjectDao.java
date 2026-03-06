package com.example.roomapp1.persistence.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.roomapp1.persistence.model.Category;
import com.example.roomapp1.persistence.model.CategoryAndIssues;
import com.example.roomapp1.persistence.model.Project;
import com.example.roomapp1.persistence.model.ProjectIssueCrossRef;
import com.example.roomapp1.persistence.model.ProjectWithIssues;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM project ORDER BY projectId ASC")
    public Flowable<List<Project>> getAll();

    @Insert
    public Completable insert(Project project);

    @Insert
    public Completable insertIssueCrossRef(ProjectIssueCrossRef crossRef);

    @Query("SELECT * FROM ProjectIssueCrossRef")
    public Flowable<List<ProjectIssueCrossRef>> getProjectIssueCrossRefs();

    @Transaction
    @Query("SELECT * FROM project")
    public Flowable<List<ProjectWithIssues>> getProjectsWithIssues();

    @Transaction
    @Query("SELECT * FROM project WHERE projectId = :projectId")
    public Flowable<ProjectWithIssues> getProjectWithIssues(long projectId);
}
