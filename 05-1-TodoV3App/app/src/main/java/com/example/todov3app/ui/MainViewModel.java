package com.example.todov3app.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todov3app.model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Task>> tasks;

    public LiveData<List<Task>> getTasks() {
        if (tasks == null) {
            tasks = new MutableLiveData<List<Task>>();
            tasks.setValue(Arrays.asList(
                    new Task("task 1", ""),
                    new Task("task 2", "")
            ));
        }
        return tasks;
    }

    public void addTask(String name, String description) {
        // copy previous list
        List<Task> newList = new ArrayList<Task>(tasks.getValue());
        newList.add(new Task(name, description));
        tasks.setValue(newList);
    }

    public Task getTask(int index) {
        return tasks.getValue().get(index);
    }

    public void removeTask(int index) {
        List<Task> newList = new ArrayList<Task>(tasks.getValue());
        newList.remove(index);
        tasks.setValue(newList);
    }
}
