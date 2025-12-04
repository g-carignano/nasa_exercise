package com.carignano.nasa_exercise.service.client.awc;

import com.carignano.nasa_exercise.dto.awc.AirportInfo;
import com.carignano.nasa_exercise.dto.awc.StationInfo;
import com.carignano.nasa_exercise.util.BoxCoordinates;

import java.util.List;

public interface IAwcClientService {
    List<StationInfo> getClosestByStations(String airportid, BoxCoordinates boxCoordinates);
    AirportInfo getAirportInfo(String airportid);
    List<AirportInfo> getClosestByAirports(String stationId, BoxCoordinates boxCoordinates);
    StationInfo getStationInfo(String stationId);
}
