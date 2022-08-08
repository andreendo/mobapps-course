package com.example.roomapp1.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomapp1.persistence.dao.CategoryDao;
import com.example.roomapp1.persistence.dao.IssueDao;
import com.example.roomapp1.persistence.dao.ProjectDao;
import com.example.roomapp1.persistence.model.Category;
import com.example.roomapp1.persistence.model.Issue;
import com.example.roomapp1.persistence.model.Project;
import com.example.roomapp1.persistence.model.ProjectIssueCrossRef;

@Database(entities = {
        Category.class, Issue.class,
        Project.class, ProjectIssueCrossRef.class}, version = 1)
public abstract class IssuesDatabase extends RoomDatabase {

    private static volatile IssuesDatabase INSTANCE;

    public abstract CategoryDao categoryDao();

    public abstract IssueDao issueDao();

    public abstract ProjectDao projectDao();

    public static IssuesDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (IssuesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            IssuesDatabase.class, "issues.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
