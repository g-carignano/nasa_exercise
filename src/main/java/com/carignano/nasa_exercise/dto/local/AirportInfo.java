package com.carignano.nasa_exercise.dto.local;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportInfo {

    private String id;
    private String name;
    private String state;
    private String country;
    private Double latitude;
    private Double longitude;
    private Integer elevation;

}
