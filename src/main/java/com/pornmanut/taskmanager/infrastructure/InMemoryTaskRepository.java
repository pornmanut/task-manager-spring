package com.pornmanut.taskmanager.infrastructure;

import com.pornmanut.taskmanager.domain.Task;

import java.util.ArrayList;
import java.util.Optional;

public class InMemoryTaskRepository {

    private final ArrayList<Task> tasks;

    public InMemoryTaskRepository() {
        this.tasks = new ArrayList<>();
    }

    public Task save(Task task) {
        tasks.add(task);
        return task;
    }

    public Optional<Task> findById(String id) {
    return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    public ArrayList<Task> findAll() {
        return tasks;
    }

    public void delete(Task task) {
        tasks.remove(task);
    }

    public Task update(Task taskToUpdate) {
        tasks.stream()
                .filter(task -> task.getId().equals(taskToUpdate.getId()))
                .forEach(task -> {
                    task.setName(taskToUpdate.getName());
                    task.setDescription(taskToUpdate.getDescription());
                    task.setStatus(taskToUpdate.getStatus());
                });
        return taskToUpdate;
    }
}