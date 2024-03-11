package com.pornmanut.taskmanager.usecase;

import com.pornmanut.taskmanager.model.Task;
import com.pornmanut.taskmanager.repository.ITaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

public class TaskManagerTest {
    private ITaskRepository taskRepository;
    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        taskRepository = Mockito.mock(ITaskRepository.class);
        taskManager = new TaskManager(taskRepository);
    }

    @Test
    public void testCreateTask() {
        Task task = new Task("1", "Test Task", "This is a test task", Task.Status.TODO);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task createdTask = taskManager.CreateTask("Test Task", "This is a test task", Task.Status.TODO);

        assertEquals(task, createdTask);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task("1", "Test Task", "This is a test task", Task.Status.TODO);
        when(taskRepository.findById(anyString())).thenReturn(Optional.of(task));
        when(taskRepository.update(any(Task.class))).thenReturn(task);

        Task updatedTask = taskManager.UpdateTask("1", "Updated Task", "This is an updated task", Task.Status.DOING);

        assertEquals("Updated Task", updatedTask.getName());
        assertEquals("This is an updated task", updatedTask.getDescription());
        assertEquals(Task.Status.DOING, updatedTask.getStatus());
        verify(taskRepository, times(1)).findById(anyString());
        verify(taskRepository, times(1)).update(any(Task.class));
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task("1", "Test Task", "This is a test task", Task.Status.TODO);
        when(taskRepository.findById(anyString())).thenReturn(Optional.of(task));

        taskManager.DeleteTask("1");

        verify(taskRepository, times(1)).findById(anyString());
        verify(taskRepository, times(1)).delete(any(Task.class));
    }

    @Test
    public void testGetTask() {
        Task task = new Task("1", "Test Task", "This is a test task", Task.Status.TODO);
        when(taskRepository.findById(anyString())).thenReturn(Optional.of(task));

        Task retrievedTask = taskManager.GetTask("1");

        assertEquals(task, retrievedTask);
        verify(taskRepository, times(1)).findById(anyString());
    }

    @Test
    public void testGetAllTasks() {
        taskManager.GetAllTasks();

        verify(taskRepository, times(1)).findAll();
    }
}