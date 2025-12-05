package com.carignano.nasa_exercise.dto.awc;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StationInfoDTO {

    private String id;
    private String icaoId;
    private String iataId;
    private String faaId;
    private String wmoId;
    private String site;
    private Double lat;
    private Double lon;
    private Integer elev;
    private String state;
    private String country;
    private Integer priority;
    private List<String> siteType;

}
