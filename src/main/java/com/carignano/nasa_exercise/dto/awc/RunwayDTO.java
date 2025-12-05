package com.carignano.nasa_exercise.dto.awc;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RunwayDTO {

    private String id;
    private String dimension;
    private String surface;
    private Integer alignment;

}
