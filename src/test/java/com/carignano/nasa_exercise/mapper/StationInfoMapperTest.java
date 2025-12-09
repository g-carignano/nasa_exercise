package com.carignano.nasa_exercise.mapper;

import com.carignano.nasa_exercise.dto.awc.StationInfoDTO;
import com.carignano.nasa_exercise.dto.local.StationInfo;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StationInfoMapperTest {

    @InjectMocks
    StationInfoMapper stationInfoMapper = Mappers.getMapper(StationInfoMapper.class);

    @Test
    public void givenValidStationInfoDTOWhenCallingStationInfoMapperThenReturnStationInfo(){

        StationInfoDTO mockStationInfoDTO = new StationInfoDTO();
        StationInfo expectedStationInfo = new StationInfo("LIMF","Site test","IT",null,42.0,42.0,4242);

        this.setMockStationInfoDTO(mockStationInfoDTO);

        Assertions.assertEquals(expectedStationInfo,stationInfoMapper.toStationInfoLocal(mockStationInfoDTO));

    }

    @Test
    public void givenValidStationInfoDTOWithInstancioWhenCallingStationInfoMapperThenReturnStationInfo(){

        StationInfoDTO mockStationInfoDTO = Instancio.create(StationInfoDTO.class);
        StationInfo expectedStationInfo = new StationInfo(mockStationInfoDTO.getId(),mockStationInfoDTO.getSite(),mockStationInfoDTO.getState(),mockStationInfoDTO.getCountry(),mockStationInfoDTO.getLat(),mockStationInfoDTO.getLon(),mockStationInfoDTO.getElev());

        Assertions.assertEquals(expectedStationInfo,stationInfoMapper.toStationInfoLocal(mockStationInfoDTO));

    }

    @Test
    public void givenListValidStationInfoDTOWhenCallingStationInfoMapperThenReturnListStationInfo(){
        List<StationInfoDTO> mockListStationInfoDTO = new ArrayList<>();
        List<StationInfo> expectedListStationInfo = new ArrayList<>();

        for(int i = 0; i < 3; i++){
            expectedListStationInfo.add(new StationInfo("LIMF","Site test","IT",null,42.0,42.0,4242));
            mockListStationInfoDTO.add(this.getNewMockStationInfoDTO());
        }

        Assertions.assertEquals(expectedListStationInfo,stationInfoMapper.toListStationInfoLocal(mockListStationInfoDTO));
    }

    private void setMockStationInfoDTO(StationInfoDTO stationInfoDTO){
        stationInfoDTO.setId("LIMF");
        stationInfoDTO.setSite("Site test");
        stationInfoDTO.setState("IT");
        stationInfoDTO.setCountry(null);
        stationInfoDTO.setLat(42.0);
        stationInfoDTO.setLon(42.0);
        stationInfoDTO.setElev(4242);
    }

    private StationInfoDTO getNewMockStationInfoDTO(){
        StationInfoDTO ret = new StationInfoDTO();
        this.setMockStationInfoDTO(ret);
        return ret;
    }

}
