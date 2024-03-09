package com.pornmanut.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TaskManagerSpring {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(TaskManagerSpring.class, args);
        System.out.println("Hello Spring Boot");
    }
}