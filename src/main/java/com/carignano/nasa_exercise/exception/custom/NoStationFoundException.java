package com.carignano.nasa_exercise.exception.custom;

import lombok.Getter;

@Getter
public class NoStationFoundException extends EntityNotFoundException {
    private static final String START_MSG = "Station with Id: %s not Found!";
    private static final Integer STATION_NOT_FOUND_CODE = 2;

    public NoStationFoundException(String stationId) {
        super(String.format(START_MSG, stationId), STATION_NOT_FOUND_CODE);
    }
}
