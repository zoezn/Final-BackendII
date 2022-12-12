package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SeriesServiceClient;
import com.dh.catalog.controller.GetByGenreResponse;
import com.dh.catalog.model.ChapterEntity;
import com.dh.catalog.model.MovieEntity;
import com.dh.catalog.model.SeasonEntity;
import com.dh.catalog.model.SeriesEntity;
import com.dh.catalog.model.dto.ChapterDTO;
import com.dh.catalog.model.dto.MovieDTO;
import com.dh.catalog.model.dto.SeasonDTO;
import com.dh.catalog.model.dto.SeriesDTO;
import com.dh.catalog.repository.MovieEntityRepository;
import com.dh.catalog.repository.SeriesEntityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {

    private final SeriesEntityRepository seriesEntityRepository;
    private final MovieEntityRepository movieEntityRepository;

    private final MovieServiceClient movieServiceClient;
    private final SeriesServiceClient seriesServiceClient;

    public CatalogService(SeriesEntityRepository seriesEntityRepository, MovieEntityRepository movieEntityRepository, MovieServiceClient movieServiceClient, SeriesServiceClient seriesServiceClient) {
        this.seriesEntityRepository = seriesEntityRepository;
        this.movieEntityRepository = movieEntityRepository;
        this.movieServiceClient = movieServiceClient;
        this.seriesServiceClient = seriesServiceClient;
    }

//    public List<SeriesEntity> findSeriesByGenre(String genre){ return  seriesEntityRepository.findByGenre(genre);};
//    public List<MovieEntity> findMovieByGenre(String genre){ return  movieEntityRepository.findByGenre(genre);};

    public GetByGenreResponse getByGenreOnline(String genre){
        GetByGenreResponse getByGenreResponse = new GetByGenreResponse();
        findAllMoviesByGenre(genre,getByGenreResponse);
        findAllSeriesByGenre(genre,getByGenreResponse);
        return getByGenreResponse;
    }

//    @Retry(name = "retryMusic")
//    @CircuitBreaker(name = "clientMusic", fallbackMethod = "findAllPlaylistFallBack")
    private void findAllMoviesByGenre(String genre, GetByGenreResponse response) {
        response.setMovies(movieServiceClient.getMovieByGenre(genre));
    }
    private void findAllSeriesByGenre(String genre, GetByGenreResponse response) {
        response.setSeries(seriesServiceClient.getSeriesByGenre(genre));
    }

    public GetByGenreResponse getByGenreOffline(String genre){
        GetByGenreResponse getByGenreResponse = new GetByGenreResponse();
        List<MovieDTO> movies = new ArrayList<>();
        List<SeriesDTO> series = new ArrayList<>();


        MovieDTO movieDTO = new MovieDTO();

        for (MovieEntity m: movieEntityRepository.findByGenre(genre)
             ) {
            BeanUtils.copyProperties(m, movieDTO);
            movies.add(movieDTO);
            System.out.println(movies);
        }

        for (SeriesEntity s : seriesEntityRepository.findByGenre(genre)){
            SeriesDTO seriesDTO = new SeriesDTO();
            BeanUtils.copyProperties(s, seriesDTO);
            for (SeasonEntity s2 :
                    s.getSeasons()) {
                SeasonDTO sDTO = new SeasonDTO();
                BeanUtils.copyProperties(s2, sDTO);
                for (ChapterEntity c :
                        s2.getChapters()) {
                    ChapterDTO cDTO = new ChapterDTO();
                    BeanUtils.copyProperties(c, cDTO);
                    sDTO.getChapters().add(cDTO);
                }

                seriesDTO.getSeasons().add(sDTO);
            }
            series.add(seriesDTO);
            System.out.println(series);
        }


        return getByGenreResponse;

    }

}
