package com.carignano.nasa_exercise.dto.awc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportInfoDTO {

    private String icaoId;
    private String iataId;
    private String faaId;
    private String name;
    private String state;
    private String country;
    private String source;
    private String type;
    private Double lat;
    private Double lon;
    private Integer elev;
    private String magdec;
    private String owner;
    private String rwyNum;
    private String rwyLength;
    private String rwyType;
    private String services;
    private String tower;
    private String beacon;
    private String freqs;
    private String priority;
    private List<RunwayDTO> runwayDTOS;

}
