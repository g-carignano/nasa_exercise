package com.carignano.nasa_exercise.exception.custom;

import lombok.Getter;

@Getter
public class NoAirportFoundException extends EntityNotFoundException {
    private static final String START_MSG = "Airport with Id: %s not Found!";
    private static final Integer AIRPORT_NOT_FOUND_CODE = 1;

    public NoAirportFoundException(String airportId) {
        super(String.format(START_MSG, airportId), AIRPORT_NOT_FOUND_CODE);
    }
}
