package com.pornmanut.taskmanager;

public class TaskFactory {
    public static Task createTask(String name, String description, Task.Status status) {
        return new Task(name, description, status);
    }
}