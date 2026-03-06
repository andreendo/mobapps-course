package com.example.roomapp1.persistence.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryAndIssues {

    @Embedded
    public Category category;

    @Relation(
            parentColumn = "categoryId",
            entityColumn = "categoryId"
    )
    public List<Issue> issues;
}
