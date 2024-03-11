package com.pornmanut.taskmanager.usecase;

import com.pornmanut.taskmanager.model.Task;
import com.pornmanut.taskmanager.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;



@Service("TaskManager")
public class TaskManager {
    private final ITaskRepository taskRepository;

    @Autowired
    public TaskManager(@Qualifier("DynamoDbTaskRepository") ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task CreateTask(String name, String description, Task.Status status) {
        String id = UUID.randomUUID().toString();
        Task task = new Task(id, name, description, status);
        return taskRepository.save(task);
    }

    public Task UpdateTask(String id, String name, String description, Task.Status status) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setName(name);
        task.setDescription(description);
        task.setStatus(status);
        return taskRepository.update(task);
    }

    public void DeleteTask(String id) {
        Task task = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(task);
    }

    public Task GetTask(String id) {
        return taskRepository.findById(id).orElseThrow();
    }

    public Iterable<Task> GetAllTasks() {
        return taskRepository.findAll();
    }
}

