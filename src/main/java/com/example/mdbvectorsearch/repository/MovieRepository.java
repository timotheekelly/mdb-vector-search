package com.example.mdbvectorsearch.repository;

import java.util.List;

import com.example.mdbvectorsearch.model.Movie;

import reactor.core.publisher.Mono;

public interface MovieRepository {
	Mono<List<Movie>> getMoviesSemanticSearch(String plotDescription);
}
