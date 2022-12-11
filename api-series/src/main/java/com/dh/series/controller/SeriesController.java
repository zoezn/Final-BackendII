package com.dh.series.controller;


import com.dh.series.model.Series;
import com.dh.series.service.SeriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/series")
public class SeriesController {

    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Series>> getMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(seriesService.findByGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Series> saveMovie(@RequestBody Series series) {
        return ResponseEntity.ok().body(seriesService.save(series));
    }
}