package com.pornmanut.taskmanager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

@Configuration
public class DynamoDbConfig {

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Value("${dynamodb.tableName}")
    private String tableName;

    public DynamoDbClient dynamoDbClient() {
        DynamoDbClientBuilder builder = DynamoDbClient.builder();
        if (System.getenv("AWS_EXECUTION_ENV") == null) {
            builder.credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)));
        }
        builder.region(Region.of(region));
        return builder.build();
    }

    public String tableName() {
        return tableName;
    }
}