package com.example.roomapp1.persistence.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.roomapp1.persistence.model.Category;
import com.example.roomapp1.persistence.model.CategoryAndIssues;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM category ORDER BY categoryId ASC")
    public Flowable<List<Category>> getAll();

    @Insert
    public Completable insert(Category category);

    @Transaction
    @Query("SELECT * FROM category WHERE categoryId = :categoryId ORDER BY categoryId ASC")
    public Single<CategoryAndIssues> getCategoryWithIssues(long categoryId);
}
