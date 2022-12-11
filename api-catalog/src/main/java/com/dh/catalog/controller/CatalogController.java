package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SeriesServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final MovieServiceClient movieServiceClient;

	private final SeriesServiceClient seriesServiceClient;

	public CatalogController(MovieServiceClient movieServiceClient, SeriesServiceClient seriesServiceClient) {
		this.movieServiceClient = movieServiceClient;
		this.seriesServiceClient = seriesServiceClient;
	}

	@GetMapping("/movie/{genre}")
	ResponseEntity<List<MovieServiceClient.MovieDto>> getMovieGenre(@PathVariable String genre) {
		return ResponseEntity.ok(movieServiceClient.getMovieByGenre(genre));
	}
	@GetMapping("/series/{genre}")
	ResponseEntity<List<SeriesServiceClient.SeriesEntity>> getSeriesGenre(@PathVariable String genre) {
		return ResponseEntity.ok(seriesServiceClient.getSeriesByGenre(genre));
	}

}