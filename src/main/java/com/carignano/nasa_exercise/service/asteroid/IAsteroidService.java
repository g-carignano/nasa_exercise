package com.carignano.nasa_exercise.service.asteroid;

import com.carignano.nasa_exercise.dto.local.AsteroidPath;

import java.time.LocalDate;
import java.util.List;

public interface IAsteroidService {
    List<AsteroidPath> getAsteroidPaths(Long asteroidId, LocalDate fromDate, LocalDate toDate);
}
