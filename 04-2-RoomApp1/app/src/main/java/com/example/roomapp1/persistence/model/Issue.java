package com.example.roomapp1.persistence.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Issue {
    @PrimaryKey(autoGenerate = true)
    public long issueId;

    public String name;

    public String description;

    public long categoryId;

    public Issue() {}

    public Issue(long issueId, String pName, String pDescription, long categoryId) {
        this.issueId = issueId;
        this.name = pName;
        this.description = pDescription;
        this.categoryId = categoryId;
    }

    public Issue(String pName, String pDescription, long categoryId) {
        this.name = pName;
        this.description = pDescription;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "issueId=" + issueId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
