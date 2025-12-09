package com.carignano.nasa_exercise.service.awc;

import com.carignano.nasa_exercise.dto.awc.AirportInfoDTO;
import com.carignano.nasa_exercise.dto.awc.StationInfoDTO;
import com.carignano.nasa_exercise.dto.local.StationInfo;
import com.carignano.nasa_exercise.mapper.StationInfoMapper;
import com.carignano.nasa_exercise.service.awc.airport.AirportService;
import com.carignano.nasa_exercise.service.client.awc.IAwcClientService;
import com.carignano.nasa_exercise.util.BoxCoordinates;
import com.carignano.nasa_exercise.util.CoordinatesCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    @InjectMocks
    private AirportService airportService;
    @Mock
    private IAwcClientService awcClientService;
    @Mock
    private StationInfoMapper stationInfoMapper;

    @Test
    public void givenAirportIdAndRangeWhenCallingAwcClientServiceThenReturnStationInfoList(){

        //Given
        String airportId = "LIMF";
        Double range = 2.0;
        BoxCoordinates boxCoordinatesAirport;

        AirportInfoDTO mockAirportInfoDTO = new AirportInfoDTO();
        mockAirportInfoDTO.setLat(0.0);
        mockAirportInfoDTO.setLon(0.0);

        List<StationInfoDTO> mockListStationInfoDTO = List.of(new StationInfoDTO());

        List<StationInfo> expectedListStationInfo = List.of(new StationInfo("61001","Nice Bouy", "", "FR", 43.4, 7.8, 0));

        boxCoordinatesAirport = CoordinatesCalculator.getBoxCoordinatesByRange(mockAirportInfoDTO.getLat(), mockAirportInfoDTO.getLon(), range);

        Mockito.when(awcClientService.getAirportInfo(airportId)).thenReturn(mockAirportInfoDTO);
        Mockito.when(awcClientService.getClosestByStations(airportId,boxCoordinatesAirport)).thenReturn(mockListStationInfoDTO);
        Mockito.when(stationInfoMapper.toListStationInfoLocal(mockListStationInfoDTO)).thenReturn(expectedListStationInfo);

        //When
        List<StationInfo> listStationInfo = airportService.getClosestByStations(airportId,range);

        //Then
        Assertions.assertEquals(expectedListStationInfo, listStationInfo, "StationInfo list should be equal");
    }

    @Test
    public void givenNullObjects(){
        Assertions.assertThrows(NullPointerException.class, () -> {
            airportService.getClosestByStations(null, null);
        });
    }

}
