package com.carignano.nasa_exercise.dto.local;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsteroidPath {
    private String fromPlanet;
    private String toPlanet;
    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate fromDate;
    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate toDate;
}
