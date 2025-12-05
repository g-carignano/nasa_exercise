package com.carignano.nasa_exercise.mapper;

import com.carignano.nasa_exercise.dto.awc.StationInfoDTO;
import com.carignano.nasa_exercise.dto.local.StationInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationInfoMapper {

    @Mapping(source = "lat", target = "latitude")
    @Mapping(source = "lon", target = "longitude")
    @Mapping(source = "elev", target = "elevation")
    StationInfo toStationInfoLocal(StationInfoDTO stationInfoDTO);

    List<StationInfo> toListStationInfoLocal(List<StationInfoDTO> stationInfoDTOList);
}
