package com.dh.catalog.repository;

import com.dh.catalog.client.SeriesServiceClient;
import com.dh.catalog.model.SeriesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesEntityRepository extends MongoRepository<SeriesEntity, String> {

    List<SeriesEntity> findByGenre(String genre);
}
