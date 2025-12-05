package com.carignano.nasa_exercise.service.client.awc;

import com.carignano.nasa_exercise.dto.awc.AirportInfoDTO;
import com.carignano.nasa_exercise.dto.awc.StationInfoDTO;
import com.carignano.nasa_exercise.util.BoxCoordinates;

import java.util.List;

public interface IAwcClientService {
    List<StationInfoDTO> getClosestByStations(String airportid, BoxCoordinates boxCoordinates);
    AirportInfoDTO getAirportInfo(String airportid);
    List<AirportInfoDTO> getClosestByAirports(String stationId, BoxCoordinates boxCoordinates);
    StationInfoDTO getStationInfo(String stationId);
}
