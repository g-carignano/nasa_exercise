package com.carignano.nasa_exercise.dto.nasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrbitalData {
    private String orbit_id;
    private String orbit_determination_date;
    private String first_observation_date;
    private String last_observation_date;
    private String data_arc_in_days;
    private String observations_used;
    private String eccentricity;
    private String semi_major_axis;
    private String inclination;
}
