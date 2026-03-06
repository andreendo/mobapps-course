package com.example.roomapp1.persistence.repo;

import android.content.Context;

import com.example.roomapp1.persistence.IssuesDatabase;
import com.example.roomapp1.persistence.model.Category;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    private IssuesDatabase db;

    public Repository(Context context) {
        db = IssuesDatabase.getInstance(context);
    }

    public void addCategory(ICategory iCategory) {
        Category c = new Category(iCategory.getName());
        db.categoryDao().insert(c)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {

                },  throwable -> {

                });
    }

    public void getCategories(CategoriesCallback cb) {
        db.categoryDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {
                    ArrayList<ICategory> res = new ArrayList<>();
                    for(Category c : categories) {
                        res.add(new ICategory(c.categoryId, c.name));
                    }
                    cb.onSuccess(res);
                }, throwable -> {
                    cb.onError(throwable.getMessage());
                });
    }

    public interface CategoriesCallback {
        public void onSuccess(List<ICategory> categories);
        public void onError(String error);
    }
}
