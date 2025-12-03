package com.carignano.nasa_exercise.service.client;

import com.carignano.nasa_exercise.dto.nasa.AsteroidInfoNasa;

public interface IClientService {
    AsteroidInfoNasa getAsteroidInfo(Long asteroidId);
}
