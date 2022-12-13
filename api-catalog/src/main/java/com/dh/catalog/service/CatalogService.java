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
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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

//    Inicialmente quise configurar el circuit breaker para los metodos utilizados dentro de este metodo
//    para que la caída de 1 solo servicio no afectara al otro, sin embargo no pude hacer que funcione el
//    fallback de cada metodo (ni siquiera entraba al metodo de fallback). Aun así, dejé el código y los
//    metodos comentados.
//    Finalmente opté por configurarlo en este metodo, de manera que, por ejemplo, el servicio de series no
//    se encuentre disponible, el metodo consultará el server de movies y luego el de series, y al ver que
//    series no se encuentra disponible, consultará su propia base de datos para ambos metodos, descartando el
//    resultado de movies también.
    @Retry(name = "retryMovies")
    @CircuitBreaker(name = "clientMovies", fallbackMethod = "getByGenreOnlineFallback")
    public GetByGenreResponse getByGenreOnline(String genre){
        GetByGenreResponse getByGenreResponse = new GetByGenreResponse();
        findAllMoviesByGenre(genre,getByGenreResponse);
        findAllSeriesByGenre(genre,getByGenreResponse);
        return getByGenreResponse;
    }

    public GetByGenreResponse getByGenreOffline(String genre){
        GetByGenreResponse getByGenreResponse = new GetByGenreResponse();
        List<MovieDTO> movies = new ArrayList<>();
        List<SeriesDTO> series = new ArrayList<>();

        for (MovieEntity m: movieEntityRepository.findByGenre(genre)
             ) {
            MovieDTO movieDTO = new MovieDTO();
            BeanUtils.copyProperties(m, movieDTO);
            movies.add(movieDTO);
        }

        for (SeriesEntity s : seriesEntityRepository.findByGenre(genre)){
            SeriesDTO seriesDTO = new SeriesDTO();
            for (SeasonEntity s2 :
                    s.getSeasons()) {
                SeasonDTO sDTO = new SeasonDTO();

                for (ChapterEntity c :
                        s2.getChapters()) {
                    ChapterDTO cDTO = new ChapterDTO();
                    BeanUtils.copyProperties(c, cDTO);
                    sDTO.getChapters().add(cDTO);
                }
                BeanUtils.copyProperties(s2, sDTO);
                seriesDTO.getSeasons().add(sDTO);
            }
            BeanUtils.copyProperties(s, seriesDTO);
            series.add(seriesDTO);
        }

        getByGenreResponse.setMovies(movies);
        getByGenreResponse.setSeries(series);

        return getByGenreResponse;

    }
    public GetByGenreResponse getByGenreOnlineFallback(String genre, Throwable t){
        GetByGenreResponse getByGenreResponse = new GetByGenreResponse();
        List<MovieDTO> movies = new ArrayList<>();
        List<SeriesDTO> series = new ArrayList<>();

        for (MovieEntity m: movieEntityRepository.findByGenre(genre)
             ) {
            MovieDTO movieDTO = new MovieDTO();
            BeanUtils.copyProperties(m, movieDTO);
            movies.add(movieDTO);
        }

        for (SeriesEntity s : seriesEntityRepository.findByGenre(genre)){
            SeriesDTO seriesDTO = new SeriesDTO();
            for (SeasonEntity s2 :
                    s.getSeasons()) {
                SeasonDTO sDTO = new SeasonDTO();

                for (ChapterEntity c :
                        s2.getChapters()) {
                    ChapterDTO cDTO = new ChapterDTO();
                    BeanUtils.copyProperties(c, cDTO);
                    sDTO.getChapters().add(cDTO);
                }
                BeanUtils.copyProperties(s2, sDTO);
                seriesDTO.getSeasons().add(sDTO);
            }
            BeanUtils.copyProperties(s, seriesDTO);
            series.add(seriesDTO);
        }

        getByGenreResponse.setMovies(movies);
        getByGenreResponse.setSeries(series);

        return getByGenreResponse;

    }

    //    @Retry(name = "retrySeries")
    //    @CircuitBreaker(name = "clientSeries", fallbackMethod = "findAllSeriesByGenreFallBack")
    public void findAllSeriesByGenre(String genre, GetByGenreResponse response) {
        response.setSeries(seriesServiceClient.getSeriesByGenre(genre));
    }

    //    @Retry(name = "retryMovies")
    //    @CircuitBreaker(name = "clientMovies", fallbackMethod = "findAllMoviesByGenreFallBack")
    public void findAllMoviesByGenre(String genre, GetByGenreResponse response) {
        response.setMovies(movieServiceClient.getMovieByGenre(genre));
    }
    //    public void findAllMoviesByGenreFallBack(String genre, GetByGenreResponse response, Throwable t) {
    //
    //        List<MovieDTO> movies = new ArrayList<>();
    //
    //        for (MovieEntity m: movieEntityRepository.findByGenre(genre)
    //        ) {
    //            MovieDTO movieDTO = new MovieDTO();
    //            BeanUtils.copyProperties(m, movieDTO);
    //            movies.add(movieDTO);
    //        }
    //        response.setMovies(movies);
    //
    //    }



    //    public List<SeriesDTO> findAllSeriesByGenreFallBack(String genre, GetByGenreResponse response, Throwable t) {
    //        List<SeriesDTO> series = new ArrayList<>();
    //
    //        for (SeriesEntity s : seriesEntityRepository.findByGenre(genre)){
    //            SeriesDTO seriesDTO = new SeriesDTO();
    //            for (SeasonEntity s2 :
    //                    s.getSeasons()) {
    //                SeasonDTO sDTO = new SeasonDTO();
    //
    //                for (ChapterEntity c :
    //                        s2.getChapters()) {
    //                    ChapterDTO cDTO = new ChapterDTO();
    //                    BeanUtils.copyProperties(c, cDTO);
    //                    sDTO.getChapters().add(cDTO);
    //                }
    //                BeanUtils.copyProperties(s2, sDTO);
    //                seriesDTO.getSeasons().add(sDTO);
    //            }
    //            BeanUtils.copyProperties(s, seriesDTO);
    //            series.add(seriesDTO);
    //        }
    //
    //        response.setSeries(series);
    //        return response.getSeries();
    //    }

    }
