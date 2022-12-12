package com.dh.catalog.client;

import com.dh.catalog.model.dto.SeriesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@FeignClient(name="api-series")
public interface SeriesServiceClient {

    @GetMapping("/api/v1/series/{genre}")
    List<SeriesDTO> getSeriesByGenre(@PathVariable(value = "genre") String genre);


}
