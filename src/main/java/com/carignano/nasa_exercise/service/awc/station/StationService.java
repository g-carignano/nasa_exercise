package com.carignano.nasa_exercise.service.awc.station;

import com.carignano.nasa_exercise.dto.local.AirportInfo;
import com.carignano.nasa_exercise.mapper.AirportInfoMapper;
import com.carignano.nasa_exercise.service.client.awc.IAwcClientService;
import com.carignano.nasa_exercise.util.BoxCoordinates;
import com.carignano.nasa_exercise.util.CoordinatesCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService implements IStationService {
    private final IAwcClientService awcClientService;
    @Autowired
    private AirportInfoMapper airportInfoMapper;

    public StationService(IAwcClientService awcClientService) {
        this.awcClientService = awcClientService;
    }

    @Override
    public List<AirportInfo> getClosestByStations(String stationId, Double range) {
        BoxCoordinates boxCoordinatesStation;
        com.carignano.nasa_exercise.dto.awc.StationInfo stationInfo;
        List<com.carignano.nasa_exercise.dto.awc.StationInfo> result;

        stationInfo = awcClientService.getStationInfo(stationId);

        boxCoordinatesStation = CoordinatesCalculator.getBoxCoordinatesByRange(stationInfo.getLat(), stationInfo.getLon(), range);

        return airportInfoMapper.toListAirportInfoLocal(awcClientService.getClosestByAirports(stationId, boxCoordinatesStation));
    }
}
