package com.carignano.nasa_exercise.service.awc.airport;

import com.carignano.nasa_exercise.dto.local.StationInfo;

import java.util.List;

public interface IAirportService {
    List<StationInfo> getClosestByStations(String airportId, Double range);
}
