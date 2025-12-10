package com.carignano.nasa_exercise.service.client.awc;

import com.carignano.nasa_exercise.dto.awc.AirportInfoDTO;
import com.carignano.nasa_exercise.dto.awc.StationInfoDTO;
import com.carignano.nasa_exercise.exception.custom.NoAirportFoundException;
import com.carignano.nasa_exercise.exception.custom.NoStationFoundException;
import com.carignano.nasa_exercise.util.BoxCoordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class AwcClientService implements IAwcClientService {

    @Value("${awc.station.info}")
    private String getStationInfoUrl;
    @Value("${awc.station.airportinfoarea}")
    private String getStationAreaInfoUrl;

    @Value("${awc.airport.info}")
    private String getAirportInfoUrl;
    @Value("${awc.airport.stationinfoarea}")
    private String getAirportAreaInfoUrl;


    private static final Logger log = LoggerFactory.getLogger(AwcClientService.class);

    private final RestClient awcRestClient;

    public AwcClientService(RestClient awcRestClient){
        this.awcRestClient = awcRestClient;
    }

    @Override
    public List<StationInfoDTO> getClosestByStations(String airportid, BoxCoordinates boxCoordinates) {
        log.info("Calling external api: {} with airportid: {} lat0: {} lon0: {} lat1: {} lon1:{}",getStationInfoUrl, airportid, boxCoordinates.getLat0(), boxCoordinates.getLon0(), boxCoordinates.getLat1(), boxCoordinates.getLon1());

        return awcRestClient.get()
                .uri(getStationAreaInfoUrl, boxCoordinates.getLat0(), boxCoordinates.getLon0(), boxCoordinates.getLat1(), boxCoordinates.getLon1())
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<StationInfoDTO>>() {})
                .getBody();
    }

    @Override
    public AirportInfoDTO getAirportInfo(String airportid) {
        log.info("Calling external api: {} with airportid: {} with box 0",getStationInfoUrl, airportid);

        List<AirportInfoDTO> airportInfoDTOList = awcRestClient.get()
                .uri(getAirportInfoUrl, airportid)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<AirportInfoDTO>>() {})
                .getBody();

        if(airportInfoDTOList == null || airportInfoDTOList.isEmpty()){
            log.error("No airport with id: {} was found!", airportid);
            throw new NoAirportFoundException(airportid);
        }

        return airportInfoDTOList.get(0);
    }

    @Override
    public List<AirportInfoDTO> getClosestByAirports(String stationId, BoxCoordinates boxCoordinates) {
        log.info("Calling external api: {} with airportid: {} lat0: {} lon0: {} lat1: {} lon1:{}",getStationInfoUrl, stationId, boxCoordinates.getLat0(), boxCoordinates.getLon0(), boxCoordinates.getLat1(), boxCoordinates.getLon1());

        return awcRestClient.get()
                .uri(getAirportAreaInfoUrl, boxCoordinates.getLat0(), boxCoordinates.getLon0(), boxCoordinates.getLat1(), boxCoordinates.getLon1())
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<AirportInfoDTO>>() {})
                .getBody();
    }

    @Override
    public StationInfoDTO getStationInfo(String stationId) {
        log.info("Calling external api: {} with airportid: {} with box 0",getStationInfoUrl, stationId);

        List<StationInfoDTO> stationInfoDTOList = awcRestClient.get()
                .uri(getStationInfoUrl, stationId)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<StationInfoDTO>>() {})
                .getBody();

        if(stationInfoDTOList == null || stationInfoDTOList.isEmpty()){
            log.error("No station with id: {} was found!", stationId);
            throw new NoStationFoundException(stationId);
        }

        return stationInfoDTOList.get(0);
    }

}
