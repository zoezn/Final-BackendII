package com.dh.catalog.client;

import com.dh.catalog.model.dto.MovieDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="api-movie")
public interface MovieServiceClient {

	@GetMapping("/api/v1/movies/{genre}")
	List<MovieDTO> getMovieByGenre(@PathVariable (value = "genre") String genre);




}