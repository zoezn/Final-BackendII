package com.dh.series.model;

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
public class Season implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String name;

    private Integer seasonNumber;

    private List<Chapter> chapters= new ArrayList<>();


}
