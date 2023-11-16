package com.example.mdbvectorsearch.service;

import java.util.List;

import com.mongodb.client.model.search.FieldSearchPath;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.search.SearchPath.fieldPath;
import static java.util.Arrays.asList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mdbvectorsearch.model.Movie;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieService {

	private final MongoDatabase mongoDatabase;
	private final OpenAIService embedder;
	
	
	@Autowired
	public MovieService(MongoDatabase mongoDatabase, OpenAIService embedder) {
		this.mongoDatabase = mongoDatabase;
		this.embedder = embedder;
	}

	public Mono<List<Movie>> getMoviesSemanticSearch(String plotDescription) {

		// create the embedding using our OpenAI service
		return embedder.createEmbedding(plotDescription)
				.flatMapMany(embedding -> queryMoviesByVector(embedding))
				.collectList();
	}

	private MongoCollection<Document> getMovieCollection() {
	    return mongoDatabase.getCollection("embedded_movies");
	}

	private Flux<Movie> queryMoviesByVector(List<Double> embedding) {
		
		// Our Vector Search aggregation 
		String indexName = "PlotVectorSearch";
		FieldSearchPath fieldSearchPath = fieldPath("plot_embedding");
		int numCandidates = 100;
		int limit = 5;

		List<Bson> pipeline = asList(
				vectorSearch(
						fieldSearchPath,
						embedding,
						indexName,
						numCandidates,
						limit));

		return Flux.from(getMovieCollection().aggregate(pipeline))
	               .map(this::documentToMovie); // Convert each Document to a Movie
	}

	private Movie documentToMovie(Document document) {
		// Manually convert Document to Movie
		Movie movie = new Movie();
		movie.set_id(document.getObjectId("_id").toString());
		movie.setTitle(document.getString("title"));
		movie.setPlot(document.getString("plot"));
		// Set other fields...
		return movie;
	}
}