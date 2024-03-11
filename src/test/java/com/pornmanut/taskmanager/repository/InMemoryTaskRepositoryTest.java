package com.pornmanut.taskmanager.repository;

import com.pornmanut.taskmanager.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryTaskRepositoryTest {

    private InMemoryTaskRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new InMemoryTaskRepository();
    }

    @Test
    public void testSaveAndFindById() {
        Task task = new Task(
                "1",
                "Test Task",
                "This is a test task",
                Task.Status.TODO
        );

        repository.save(task);

        Optional<Task> foundTask = repository.findById("1");

        assertTrue(foundTask.isPresent());
        assertEquals("1", foundTask.get().getId());
        assertEquals("Test Task", foundTask.get().getName());
        assertEquals("This is a test task", foundTask.get().getDescription());
        assertEquals(Task.Status.TODO, foundTask.get().getStatus());
    }

    @Test
    public void testUpdate() {
        Task task = new Task(
                "1",
                "Test Task",
                "This is a test task",
                Task.Status.TODO
        );

        repository.save(task);

        Task updatedTask = new Task(
                "1",
                "Updated Task",
                "This is an updated task",
                Task.Status.DOING
        );

        repository.update(updatedTask);

        Optional<Task> foundTask = repository.findById("1");

        assertTrue(foundTask.isPresent());
        assertEquals("1", foundTask.get().getId());
        assertEquals("Updated Task", foundTask.get().getName());
        assertEquals("This is an updated task", foundTask.get().getDescription());
        assertEquals(Task.Status.DOING, foundTask.get().getStatus());
    }

    @Test
    public void testDelete() {
        Task task = new Task(
                "1",
                "Test Task",
                "This is a test task",
                Task.Status.TODO
        );

        repository.save(task);

        repository.delete(task);

        Optional<Task> foundTask = repository.findById("1");

        assertTrue(foundTask.isEmpty());
    }

    @Test
    public void testFindAll() {
        Task task1 = new Task(
                "1",
                "Test Task 1",
                "This is a test task 1",
                Task.Status.TODO
        );

        Task task2 = new Task(
                "2",
                "Test Task 2",
                "This is a test task 2",
                Task.Status.DOING
        );

        repository.save(task1);
        repository.save(task2);

        assertEquals(2, repository.findAll().size());
    }
}