package com.dh.movie.event;

import com.dh.movie.config.RabbitMQConfig;
import com.dh.movie.model.Movie;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;

@Component
@Slf4j
public class NewMovieEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public NewMovieEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void execute(Movie movieNew) {
        NewMovieEventProducer.Data data= new NewMovieEventProducer.Data();
        BeanUtils.copyProperties(movieNew,data.getMovie());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_NEW_MUSIC, data);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;
        private Data.MovieDTO movie= new Data.MovieDTO();


        @lombok.Data
        @AllArgsConstructor
        @NoArgsConstructor
        @Entity
        public class MovieDTO implements Serializable {

            private static final long serialVersionUID = 1L;

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;

            private String name;

            private String genre;

            private String urlStream;

        }

    }
}