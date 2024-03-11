package com.pornmanut.taskmanager.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task("1", "Test Task", "This is a test task", Task.Status.TODO);
    }

    @Test
    public void testGetId() {
        assertEquals("1", task.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Test Task", task.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("This is a test task", task.getDescription());
    }

    @Test
    public void testGetStatus() {
        assertEquals(Task.Status.TODO, task.getStatus());
    }

    @Test
    public void testSetId() {
        task.setId("2");
        assertEquals("2", task.getId());
    }

    @Test
    public void testSetName() {
        task.setName("Updated Task");
        assertEquals("Updated Task", task.getName());
    }

    @Test
    public void testSetDescription() {
        task.setDescription("This is an updated task");
        assertEquals("This is an updated task", task.getDescription());
    }

    @Test
    public void testSetStatus() {
        task.setStatus(Task.Status.DOING);
        assertEquals(Task.Status.DOING, task.getStatus());
        task.setStatus(Task.Status.DONE);
        assertEquals(Task.Status.DONE, task.getStatus());
        task.setStatus(Task.Status.TODO);
        assertEquals(Task.Status.TODO, task.getStatus());
    }
}