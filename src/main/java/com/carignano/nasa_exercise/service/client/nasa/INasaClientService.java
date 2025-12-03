package com.carignano.nasa_exercise.service.client.nasa;

import com.carignano.nasa_exercise.dto.nasa.AsteroidInfoNasa;

public interface INasaClientService {
    AsteroidInfoNasa getAsteroidInfo(Long asteroidId);
}
