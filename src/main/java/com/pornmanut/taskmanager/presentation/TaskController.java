package com.pornmanut.taskmanager.presentation;

import com.pornmanut.taskmanager.application.TaskManager;
import com.pornmanut.taskmanager.domain.Task;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskManager taskManager;

    @Autowired
    public TaskController(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        Task.Status status = Task.Status.valueOf(taskRequest.getStatus());
        Task createdTask = taskManager.CreateTask(taskRequest.getName(), taskRequest.getDescription(), status);

        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @Valid @RequestBody TaskRequest taskRequest) {
        Task updatedTask = taskManager.UpdateTask(id, taskRequest.getName(),taskRequest.getDescription(),Task.Status.valueOf(taskRequest.getStatus()));
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskManager.DeleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String id) {
        Task task = taskManager.GetTask(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = (List<Task>) taskManager.GetAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}