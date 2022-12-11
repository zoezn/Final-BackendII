package com.dh.catalog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChapterDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    private Long id;

    private String name;

    private Integer number;

    private String urlStream;

}