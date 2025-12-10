package com.carignano.nasa_exercise.service.awc.station;

import com.carignano.nasa_exercise.dto.awc.StationInfoDTO;
import com.carignano.nasa_exercise.dto.local.AirportInfo;
import com.carignano.nasa_exercise.exception.custom.InvalidDataInputException;
import com.carignano.nasa_exercise.mapper.AirportInfoMapper;
import com.carignano.nasa_exercise.service.client.awc.IAwcClientService;
import com.carignano.nasa_exercise.util.BoxCoordinates;
import com.carignano.nasa_exercise.util.CoordinatesCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService implements IStationService {
    private final IAwcClientService awcClientService;
    private final AirportInfoMapper airportInfoMapper;

    @Override
    public List<AirportInfo> getClosestByStations(String stationId, Double range) {
        BoxCoordinates boxCoordinatesStation;
        StationInfoDTO stationInfoDTO;

        if(!(range > 0))
            throw new InvalidDataInputException("Range is smaller then 0! Range must be greater then 0");

        stationInfoDTO = awcClientService.getStationInfo(stationId);

        boxCoordinatesStation = CoordinatesCalculator.getBoxCoordinatesByRange(stationInfoDTO.getLat(), stationInfoDTO.getLon(), range);

        return airportInfoMapper.toListAirportInfoLocal(awcClientService.getClosestByAirports(stationId, boxCoordinatesStation));
    }
}
