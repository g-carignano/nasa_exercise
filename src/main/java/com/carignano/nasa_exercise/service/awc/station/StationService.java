package com.carignano.nasa_exercise.service.awc.station;

import com.carignano.nasa_exercise.dto.awc.StationInfoDTO;
import com.carignano.nasa_exercise.dto.local.AirportInfo;
import com.carignano.nasa_exercise.mapper.AirportInfoMapper;
import com.carignano.nasa_exercise.service.client.awc.IAwcClientService;
import com.carignano.nasa_exercise.util.BoxCoordinates;
import com.carignano.nasa_exercise.util.CoordinatesCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

        stationInfoDTO = awcClientService.getStationInfo(stationId);

        boxCoordinatesStation = CoordinatesCalculator.getBoxCoordinatesByRange(stationInfoDTO.getLat(), stationInfoDTO.getLon(), range);

        return airportInfoMapper.toListAirportInfoLocal(awcClientService.getClosestByAirports(stationId, boxCoordinatesStation));
    }
}
