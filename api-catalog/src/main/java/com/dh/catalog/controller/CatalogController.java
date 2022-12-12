package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SeriesServiceClient;
import com.dh.catalog.model.dto.MovieDTO;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final CatalogService catalogService;

	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@GetMapping("/online/{genre}")
	ResponseEntity<GetByGenreResponse> getOnlineGenre(@PathVariable String genre) {
		return ResponseEntity.ok(catalogService.getByGenreOnline(genre));
	}
	@GetMapping("/offline/{genre}")
	ResponseEntity<GetByGenreResponse> getOfflineGenre(@PathVariable String genre) {
		return ResponseEntity.ok(catalogService.getByGenreOffline(genre));
	}

}