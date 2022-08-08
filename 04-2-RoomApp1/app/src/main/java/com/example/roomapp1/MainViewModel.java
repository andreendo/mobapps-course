package com.example.roomapp1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.roomapp1.persistence.repo.ICategory;
import com.example.roomapp1.persistence.repo.Repository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Repository repo;
    private MutableLiveData<List<ICategory>> categories;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repo = new Repository(application);
    }

    public MutableLiveData<List<ICategory>> getCategories() {
        if (categories == null) {
            categories = new MutableLiveData<>();
            loadCategories();
        }
        return categories;
    }

    private void loadCategories() {
        repo.getCategories(new Repository.CategoriesCallback() {
            @Override
            public void onSuccess(List<ICategory> cats) {
                categories.setValue(cats);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    public void insertCategory(String categoryName) {
        repo.addCategory(new ICategory(categoryName));
    }
}
