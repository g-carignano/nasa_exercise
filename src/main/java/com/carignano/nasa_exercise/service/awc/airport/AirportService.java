package com.carignano.nasa_exercise.service.awc.airport;

import com.carignano.nasa_exercise.dto.awc.AirportInfo;
import com.carignano.nasa_exercise.dto.local.StationInfo;
import com.carignano.nasa_exercise.mapper.AirportInfoMapper;
import com.carignano.nasa_exercise.mapper.StationInfoMapper;
import com.carignano.nasa_exercise.service.client.awc.IAwcClientService;
import com.carignano.nasa_exercise.util.BoxCoordinates;
import com.carignano.nasa_exercise.util.CoordinatesCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService implements IAirportService{

    private final IAwcClientService awcClientService;
    @Autowired
    private StationInfoMapper stationInfoMapper;

    public AirportService(IAwcClientService awcClientService) {
        this.awcClientService = awcClientService;
    }

    @Override
    public List<StationInfo> getClosestByStations(String airportId, Double range) {
        BoxCoordinates boxCoordinatesAirport;
        AirportInfo airportInfo;
        List<com.carignano.nasa_exercise.dto.awc.StationInfo> result;

        airportInfo = awcClientService.getAirportInfo(airportId);

        boxCoordinatesAirport = CoordinatesCalculator.getBoxCoordinatesByRange(airportInfo.getLat(), airportInfo.getLon(), range);

        return stationInfoMapper.toListStationInfoLocal(awcClientService.getClosestByStations(airportId, boxCoordinatesAirport));
    }
}
