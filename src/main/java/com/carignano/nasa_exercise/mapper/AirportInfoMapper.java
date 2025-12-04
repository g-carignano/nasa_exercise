package com.carignano.nasa_exercise.mapper;

import com.carignano.nasa_exercise.dto.local.AirportInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportInfoMapper {

    @Mapping(source = "airportInfo.icaoId", target = "id")
    @Mapping(source = "airportInfo.lat", target = "latitude")
    @Mapping(source = "airportInfo.lon", target = "longitude")
    @Mapping(source = "airportInfo.elev", target = "elevation")
    AirportInfo toAirportInfoLocal(com.carignano.nasa_exercise.dto.awc.AirportInfo airportInfo);

    List<AirportInfo> toListAirportInfoLocal(List<com.carignano.nasa_exercise.dto.awc.AirportInfo> airportInfoList);
}
