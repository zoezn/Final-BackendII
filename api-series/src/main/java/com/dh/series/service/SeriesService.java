package com.dh.series.service;


import com.dh.series.event.NewSerieEventProducer;
import com.dh.series.model.Series;
import com.dh.series.repository.SeriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService {


    private final SeriesRepository seriesRepository;
    private final NewSerieEventProducer newSerieEventProducer;

    public SeriesService(SeriesRepository seriesRepository, NewSerieEventProducer newSerieEventProducer) {
        this.seriesRepository = seriesRepository;
        this.newSerieEventProducer = newSerieEventProducer;
    }

    public List<Series> findByGenre(String genre) {
        return seriesRepository.findByGenre(genre);
    }

    public Series save(Series series) {
        newSerieEventProducer.execute(series);
        return seriesRepository.save(series);
    }
}
