package com.dh.series;

import com.dh.series.model.Movie;
import com.dh.series.model.Series;
import com.dh.series.repository.SeriesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class ApiMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiMovieApplication.class, args);
    }


    @Bean
    public CommandLineRunner loadData(SeriesRepository repository) {
        return (args) -> {
            if (!repository.findAll().isEmpty()) {
                return;
            }

            repository.save(new Series(null, "Pelicula 1", "Terror", "www.netflix.com"));
            repository.save(new Series(null, "Pelicula 2", "Terror", "www.netflix.com"));
            repository.save(new Series(null, "Pelicula 3", "Comedia", "www.netflix.com"));
            repository.save(new Series(null, "Pelicula 4", "Ficcion", "www.netflix.com"));
        };
    }

}
