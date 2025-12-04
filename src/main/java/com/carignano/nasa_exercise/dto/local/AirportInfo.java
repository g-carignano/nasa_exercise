package com.carignano.nasa_exercise.dto.local;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AirportInfo {

    private String id;
    private String name;
    private String state;
    private String country;
    private Double latitude;
    private Double longitude;
    private Double elevation;

}
