package com.example.roomapp1.persistence.model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class IssueAndCategory {
    @Embedded
    public Issue issue;

    @Relation(
            parentColumn = "issueId",
            entityColumn = "categoryId"
    )
    public Category category;
}
