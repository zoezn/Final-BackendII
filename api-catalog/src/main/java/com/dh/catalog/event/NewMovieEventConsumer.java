package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConfig;
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
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class NewMovieEventConsumer {

    private final MovieEntityRepository movieEntityRepository;

    public NewMovieEventConsumer(MovieEntityRepository movieEntityRepository){
        this.movieEntityRepository = movieEntityRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NEW_MOVIE)
    public void execute(MovieDTO movieDTO) {
        MovieEntity movieNew = new MovieEntity();
        BeanUtils.copyProperties(movieDTO, movieNew);
        movieEntityRepository.deleteById(movieDTO.getId());
        movieEntityRepository.save(movieNew);
    }

}
