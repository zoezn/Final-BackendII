package com.dh.series.service;


import com.dh.series.model.Movie;
import com.dh.series.model.Series;
import com.dh.series.repository.SeriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService {


    private final SeriesRepository seriesRepository;

    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public List<Series> findByGenre(String genre) {
        return seriesRepository.findByGenre(genre);
    }

    public Series save(Series series) {
        return seriesRepository.save(series);
    }
}
