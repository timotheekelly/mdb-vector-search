package com.example.mdbvectorsearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.stereotype.Service;

import com.example.mdbvectorsearch.model.Movie;
import com.example.mdbvectorsearch.repository.MovieRepository;

import reactor.core.publisher.Mono;

@Service
public class MovieService implements MovieRepository {
	
	private final MongoTemplate mongoTemplate;
	private final OpenAIService embedder;
	
	@Autowired
	public MovieService(MongoTemplate mongoTemplate, OpenAIService embedder) {
		this.mongoTemplate = mongoTemplate;
		this.embedder = embedder;
	}
	
	@Override
	public Mono<List<Movie>> getMoviesSemanticSearch(String plotDescription) {
		
		// create the embedding using our OpenAI service
		Mono<List<Double>> embedding = embedder.createEmbedding(plotDescription);
		
		return embedding.flatMap(item -> {
			List<Movie> results = vectorSearch(item);
			return Mono.just(results);
		});
	}
	
	public List<Movie> vectorSearch(List<Double> embedding) {
	
	// Our Vector Search aggregation pipeline
		AggregationOperation aggOpp = Aggregation.stage("""
			{
			"$vectorSearch": {
				"queryVector": %s,
				"path": "plot_embedding",
				"numCandidates": 100,
				"limit": 5,
				"index": "PlotVectorSearch"
			}
		}
		""".formatted(embedding.toString()));
		
	Aggregation aggregation = Aggregation.newAggregation(
		aggOpp
	);
	
	return mongoTemplate.aggregate(aggregation, "embedded_movies", Movie.class).getMappedResults();
	}
}
