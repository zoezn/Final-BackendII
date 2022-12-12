package com.dh.catalog.controller;

import com.dh.catalog.model.SeriesEntity;
import com.dh.catalog.model.dto.MovieDTO;
import com.dh.catalog.model.dto.SeriesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetByGenreResponse {

    private List<SeriesDTO> series = new ArrayList<>();
    private List<MovieDTO> movies = new ArrayList<>();



}
