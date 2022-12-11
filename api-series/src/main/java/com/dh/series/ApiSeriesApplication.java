package com.dh.series;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class ApiSeriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiSeriesApplication.class, args);
    }


//    @Bean
//    public CommandLineRunner loadData(SeriesRepository repository) {
//        return (args) -> {
//            if (!repository.findAll().isEmpty()) {
//                return;
//            }
//
//            repository.save(new Series(null, "Pelicula 1", "Terror", "www.netflix.com"));
//            repository.save(new Series(null, "Pelicula 2", "Terror", "www.netflix.com"));
//            repository.save(new Series(null, "Pelicula 3", "Comedia", "www.netflix.com"));
//            repository.save(new Series(null, "Pelicula 4", "Ficcion", "www.netflix.com"));
//        };
//    }

}
