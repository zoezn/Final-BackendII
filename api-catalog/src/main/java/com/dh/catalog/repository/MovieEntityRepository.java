package com.dh.catalog.repository;

import com.dh.catalog.model.MovieEntity;
import com.dh.catalog.model.SeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieEntityRepository extends MongoRepository<MovieEntity, String> {
    List<MovieEntity> findByGenre(String genre);
}
