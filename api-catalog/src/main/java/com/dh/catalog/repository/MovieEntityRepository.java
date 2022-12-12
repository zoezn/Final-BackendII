package com.dh.catalog.repository;

import com.dh.catalog.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieEntityRepository extends JpaRepository<MovieEntity, Long> {
}
