package com.carignano.nasa_exercise.dto.nasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AsteroidInfoNasa {
    private OrbitalData orbital_data;
    private List<CloseApproachData> close_approach_data;
}
