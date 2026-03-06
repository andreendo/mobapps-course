package com.example.roomapp1.persistence.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomapp1.persistence.model.Issue;
import com.example.roomapp1.persistence.model.IssueAndCategory;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface IssueDao {

    @Query("SELECT * FROM issue ORDER BY issueId ASC")
    public Single<List<Issue>> getAll();

    @Insert
    public Completable insert(Issue issue);

    @Query("SELECT * FROM issue ORDER BY issueId ASC")
    public Single<List<IssueAndCategory>> getIssuesWithCategory();
}
