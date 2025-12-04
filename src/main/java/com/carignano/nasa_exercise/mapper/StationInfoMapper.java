package com.carignano.nasa_exercise.mapper;

import com.carignano.nasa_exercise.dto.local.StationInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationInfoMapper {

    @Mapping(source = "stationInfo.lat", target = "latitude")
    @Mapping(source = "stationInfo.lon", target = "longitude")
    @Mapping(source = "stationInfo.elev", target = "elevation")
    StationInfo toStationInfoLocal(com.carignano.nasa_exercise.dto.awc.StationInfo stationInfo);

    List<StationInfo> toListStationInfoLocal(List<com.carignano.nasa_exercise.dto.awc.StationInfo> stationInfoList);
}
