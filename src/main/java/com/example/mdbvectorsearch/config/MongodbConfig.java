package com.example.mdbvectorsearch.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongodbConfig {

	@Value("${mongodb.uri}")
	private String MONGODB_URI;

	@Value("${mongodb.database}")
	private String MONGODB_DATABASE;

	@Bean
	public MongoClient mongoClient() {
		// Replace with your connection string
		return MongoClients.create(MONGODB_URI);
	}

	@Bean
	public MongoDatabase mongoDatabase(MongoClient mongoClient) {
		// Replace with your database name
		return mongoClient.getDatabase(MONGODB_DATABASE); 
	}

}