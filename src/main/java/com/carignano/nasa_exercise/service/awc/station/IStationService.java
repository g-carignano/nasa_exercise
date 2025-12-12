package com.carignano.nasa_exercise.service.awc.station;

import com.carignano.nasa_exercise.dto.local.AirportInfo;
import com.carignano.nasa_exercise.dto.local.StationInfo;

import java.util.List;

public interface IStationService {
    List<AirportInfo> getClosestByAirports(String stationId, Double range);
}
