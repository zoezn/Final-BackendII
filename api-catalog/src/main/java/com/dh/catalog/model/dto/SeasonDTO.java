package com.dh.catalog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private List<ChapterDTO> chapters = new ArrayList<>();

}