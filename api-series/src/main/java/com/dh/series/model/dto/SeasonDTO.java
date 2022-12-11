package com.dh.series.model.dto;

import com.dh.series.model.ChapterDTO;
import lombok.*;
import org.springframework.data.annotation.Id;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeasonDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private Long id;

    private String name;

    private Integer seasonNumber;

    private List<ChapterDTO> chapters= new ArrayList<>();


}
