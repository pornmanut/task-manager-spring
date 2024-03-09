package com.pornmanut.taskmanager.infrastructure;

import com.pornmanut.taskmanager.domain.Task;

import java.util.ArrayList;
import java.util.Optional;

public interface ITaskRepository {

    Task save(Task task);

    Optional<Task> findById(String id);

    ArrayList<Task> findAll();

    void delete(Task task);

    Task update(Task taskToUpdate);
}