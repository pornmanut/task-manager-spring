package com.pornmanut.taskmanager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskFactoryTest {
    @Test
    public void testTaskCreation() {
        String name = "Test Task";
        String description = "This is a test task";
        Task.Status status = Task.Status.TODO;

        Task task = TaskFactory.createTask(name, description, status);

        assertEquals(name, task.getName());
        assertEquals(description, task.getDescription());
        assertEquals(status, task.getStatus());
    }
}