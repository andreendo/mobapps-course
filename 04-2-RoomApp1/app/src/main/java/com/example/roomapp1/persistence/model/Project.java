package com.example.roomapp1.persistence.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Project {
    @PrimaryKey(autoGenerate = true)
    public long projectId;

    public String name;

    public Project() {}

    public Project(long pId, String pName) {
        this.projectId = pId;
        this.name = pName;
    }

    public Project(String pName) {
        this.name = pName;
    }
}
