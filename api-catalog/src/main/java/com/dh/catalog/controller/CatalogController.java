package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SeriesServiceClient;
import com.dh.catalog.model.dto.MovieDTO;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final CatalogService catalogService;

	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@GetMapping("/online/{genre}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<GetByGenreResponse> getOnlineGenre(@PathVariable String genre) {
		return ResponseEntity.ok(catalogService.getByGenreOnline(genre));
	}
	@GetMapping("/offline/{genre}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<GetByGenreResponse> getOfflineGenre(@PathVariable String genre) {
		return ResponseEntity.ok(catalogService.getByGenreOffline(genre));
	}

}