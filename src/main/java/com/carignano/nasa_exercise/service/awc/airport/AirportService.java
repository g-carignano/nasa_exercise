package com.carignano.nasa_exercise.service.awc.airport;

import com.carignano.nasa_exercise.dto.awc.AirportInfoDTO;
import com.carignano.nasa_exercise.dto.local.StationInfo;
import com.carignano.nasa_exercise.exception.custom.InvalidDataInputException;
import com.carignano.nasa_exercise.mapper.StationInfoMapper;
import com.carignano.nasa_exercise.service.client.awc.IAwcClientService;
import com.carignano.nasa_exercise.util.BoxCoordinates;
import com.carignano.nasa_exercise.util.CoordinatesCalculator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService implements IAirportService{

    private final IAwcClientService awcClientService;
    private final StationInfoMapper stationInfoMapper;

    @Override
    @Cacheable( cacheNames = "closestStationsByAirport")
    public List<StationInfo> getClosestByStations(@NonNull String airportId, @NonNull Double range) {
        BoxCoordinates boxCoordinatesAirport;
        AirportInfoDTO airportInfoDTO;

        if(!(range > 0))
            throw new InvalidDataInputException("Range is smaller then 0! Range must be greater then 0");

        airportInfoDTO = awcClientService.getAirportInfo(airportId);

        boxCoordinatesAirport = CoordinatesCalculator.getBoxCoordinatesByRange(airportInfoDTO.getLat(), airportInfoDTO.getLon(), range);

        return stationInfoMapper.toListStationInfoLocal(awcClientService.getClosestByStations(airportId, boxCoordinatesAirport));
    }
}
