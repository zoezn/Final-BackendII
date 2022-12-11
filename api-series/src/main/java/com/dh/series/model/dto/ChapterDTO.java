package com.dh.series.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

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