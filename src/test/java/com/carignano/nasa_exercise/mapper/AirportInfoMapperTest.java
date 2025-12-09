package com.carignano.nasa_exercise.mapper;

import com.carignano.nasa_exercise.dto.awc.AirportInfoDTO;
import com.carignano.nasa_exercise.dto.local.AirportInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AirportInfoMapperTest {

    @InjectMocks
    AirportInfoMapper airportInfoMapper = Mappers.getMapper(AirportInfoMapper.class);

    @Test
    public void givenValidAirportInfoDTOWhenCallingAirportInfoMapperThenReturnAirportInfo(){
        AirportInfoDTO mockAirportInfoDTO = new AirportInfoDTO();
        AirportInfo expectedAirportInfo = new AirportInfo("LIMFP","Cuneo prova","IT","",42.42,42.42,4242);

        this.setMockAirportInfoDTO(mockAirportInfoDTO);


        Assertions.assertEquals(expectedAirportInfo, airportInfoMapper.toAirportInfoLocal(mockAirportInfoDTO));
    }

    @Test
    public void givenListValidStationInfoDTOWhenCallingStationInfoMapperThenReturnListStationInfo(){
        List<AirportInfoDTO> mockListAirportInfoDTO = new ArrayList<>();
        List<AirportInfo> expectedListAirportInfo = new ArrayList<>();

        for(int i = 0; i < 3; i++){
            expectedListAirportInfo.add(new AirportInfo("LIMFP","Cuneo prova","IT","",42.42,42.42,4242));
            mockListAirportInfoDTO.add(this.getNewMockAirportInfoDTO());
        }

        Assertions.assertEquals(expectedListAirportInfo,airportInfoMapper.toListAirportInfoLocal(mockListAirportInfoDTO));
    }

    private void setMockAirportInfoDTO(AirportInfoDTO airportInfoDTO){
        airportInfoDTO.setIcaoId("LIMFP");
        airportInfoDTO.setName("Cuneo prova");
        airportInfoDTO.setState("IT");
        airportInfoDTO.setCountry("");
        airportInfoDTO.setLat(42.42);
        airportInfoDTO.setLon(42.42);
        airportInfoDTO.setElev(4242);
    }

    private AirportInfoDTO getNewMockAirportInfoDTO(){
        AirportInfoDTO ret = new AirportInfoDTO();
        this.setMockAirportInfoDTO(ret);
        return ret;
    }

}
