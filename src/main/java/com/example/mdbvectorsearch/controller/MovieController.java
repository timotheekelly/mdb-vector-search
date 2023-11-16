package com.example.mdbvectorsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mdbvectorsearch.model.Movie;
import com.example.mdbvectorsearch.service.MovieService;

import reactor.core.publisher.Mono;

@RestController
public class MovieController {

	private final MovieService movieService;

	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping("/movies/semantic-search")
	public Mono<List<Movie>> performSemanticSearch(@RequestParam("plotDescription") String plotDescription) {
		return movieService.getMoviesSemanticSearch(plotDescription);
	}
}
