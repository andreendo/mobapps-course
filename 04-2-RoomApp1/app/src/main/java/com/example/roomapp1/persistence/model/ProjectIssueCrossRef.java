package com.example.roomapp1.persistence.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"projectId", "issueId"})
public class ProjectIssueCrossRef {
    public long projectId;
    public long issueId;

    public ProjectIssueCrossRef(long projectId, long issueId) {
        this.projectId = projectId;
        this.issueId = issueId;
    }
}
