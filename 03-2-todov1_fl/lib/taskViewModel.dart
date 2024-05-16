import 'package:flutter/material.dart';
import 'package:todov1_fl/task.dart';

class TaskViewModel extends ChangeNotifier {

  // private attr that keeps the list of tasks
  final List<Task> _tasks = [
    Task(name: "Task 1", description: "Task 1"),
    Task(name: "Task 2", description: "Task 2"),
    Task(name: "Task 3", description: "Task 3"),
  ];

  // get method
  List<Task> get tasks => _tasks;

  void addTask(String name, String description) {
    _tasks.add(Task(name: name, description: description));
    notifyListeners();
  }

  void remove(int index) {
    _tasks.removeAt(index);
    notifyListeners();
  }
}