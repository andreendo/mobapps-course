package com.example.roomapp1.persistence.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class ProjectWithIssues {
    @Embedded
    public Project project;

    @Relation(
            parentColumn = "projectId",
            entityColumn = "issueId",
            associateBy = @Junction(ProjectIssueCrossRef.class)
    )
    public List<Issue> issues;
}
