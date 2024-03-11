package com.pornmanut.taskmanager.repository;

import com.pornmanut.taskmanager.config.DynamoDbConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import com.pornmanut.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository("DynamoDbTaskRepository")
public class DynamoDbTaskRepository implements ITaskRepository{

    private final DynamoDbClient dynamoDbClient;
    private final String tableName;

    @Autowired
    public DynamoDbTaskRepository(DynamoDbConfig dynamoDbConfig) {
        this.dynamoDbClient = dynamoDbConfig.dynamoDbClient();
        this.tableName = dynamoDbConfig.tableName();
    }

    public Task save(Task task) {
        Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("id", AttributeValue.builder().s(task.getId()).build());
        itemValues.put("name", AttributeValue.builder().s(task.getName()).build());
        itemValues.put("description", AttributeValue.builder().s(task.getDescription()).build());
        itemValues.put("status", AttributeValue.builder().s(task.getStatus().toString()).build());

        System.out.println("itemValues: " + itemValues);
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(itemValues)
                .build();

        dynamoDbClient.putItem(request);
        return task;
    }

    public Task update(Task taskToUpdate) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().s(taskToUpdate.getId()).build());

        Map<String, AttributeValueUpdate> attributeUpdates = new HashMap<>();
        attributeUpdates.put("name", AttributeValueUpdate.builder().value(AttributeValue.builder().s(taskToUpdate.getName()).build()).action(AttributeAction.PUT).build());
        attributeUpdates.put("description", AttributeValueUpdate.builder().value(AttributeValue.builder().s(taskToUpdate.getDescription()).build()).action(AttributeAction.PUT).build());
        attributeUpdates.put("status", AttributeValueUpdate.builder().value(AttributeValue.builder().s(taskToUpdate.getStatus().toString()).build()).action(AttributeAction.PUT).build());

        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .attributeUpdates(attributeUpdates)
                .build();

        dynamoDbClient.updateItem(request);
        return taskToUpdate;
    }

    public void delete(Task task) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().s(task.getId()).build());

        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();

        dynamoDbClient.deleteItem(request);
    }

    public Optional<Task> findById(String id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().s(id).build());

        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();

        GetItemResponse response = dynamoDbClient.getItem(request);
        if (response.hasItem()) {
            Map<String, AttributeValue> item = response.item();
            return Optional.of(new Task(
                    item.get("id").s(),
                    item.get("name").s(),
                    item.get("description").s(),
                    Task.Status.valueOf(item.get("status").s())
            ));
        } else {
            return Optional.empty();
        }
    }

    public ArrayList<Task> findAll() {
        ScanRequest request = ScanRequest.builder()
                .tableName(tableName)
                .build();

        ScanResponse response = dynamoDbClient.scan(request);
        ArrayList<Task> tasks = new ArrayList<>();
        for (Map<String, AttributeValue> item : response.items()) {
            tasks.add(new Task(
                    item.get("id").s(),
                    item.get("name").s(),
                    item.get("description").s(),
                    Task.Status.valueOf(item.get("status").s())
            ));
        }
        return tasks;
    }


}