package com.dh.series.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;


public class Season implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    private String name;

    private Integer number;

    private String urlStream;

}
