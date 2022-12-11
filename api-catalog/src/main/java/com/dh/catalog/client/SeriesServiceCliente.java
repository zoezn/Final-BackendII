package com.dh.catalog.client;

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
public interface SeriesServiceCliente {

    @GetMapping("/api/v1/movies/{genre}")
    List<MovieServiceClient.MovieDto> getMovieByGenre(@PathVariable(value = "genre") String genre);


    @Getter
    @Setter
    class SerieDto{
        @Serial
        private static final long serialVersionUID = 1L;

        @Id
        private String id;

        private String name;

        private String genre;

        private List<SeasonDTO> seasons = new ArrayList<>();

        @AllArgsConstructor
        @NoArgsConstructor
        @Getter
        @Setter
        public class SeasonDTO implements Serializable {

            @Serial
            private static final long serialVersionUID = 1L;

            @Id
            private Long id;

            private String name;

            private Integer seasonNumber;

            private List<Chapter> chapters= new ArrayList<>();

            @AllArgsConstructor
            @NoArgsConstructor
            @Getter
            @Setter
            public class Chapter implements Serializable {
                @Serial
                private static final long serialVersionUID = 1L;

                @Id
                private Long id;

                private String name;

                private Integer number;

                private String urlStream;

            }

        }
    }
}
