package com.example.roomapp1.persistence.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    public long categoryId;

    public String name;

    public Category() {}

    public Category(long pId, String pName) {
        this.categoryId = pId;
        this.name = pName;
    }

    public Category(String pName) {
        this.name = pName;
    }
}
