package com.dh.catalog.repository;

import com.dh.catalog.client.SeriesServiceClient;
import com.dh.catalog.model.SeriesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesEntityRepository extends MongoRepository<SeriesEntity, String> {


}
