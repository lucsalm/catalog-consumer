package com.lucsalmd.catalogconsumer.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@RequiredArgsConstructor
public class MongodbConfig {

    @Value("${mongodb.connection.uri}")
    private String mongodbConnectionURL;

    @Value("${mongodb.connection.database}")
    private String mongodbDatabase;

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(mongodbConnectionURL);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), mongodbDatabase);
    }
}
