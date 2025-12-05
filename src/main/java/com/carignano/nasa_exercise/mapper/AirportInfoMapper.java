package com.carignano.nasa_exercise.mapper;

import com.carignano.nasa_exercise.dto.awc.AirportInfoDTO;
import com.carignano.nasa_exercise.dto.local.AirportInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportInfoMapper {

    @Mapping(source = "icaoId", target = "id")
    @Mapping(source = "lat", target = "latitude")
    @Mapping(source = "lon", target = "longitude")
    @Mapping(source = "elev", target = "elevation")
    AirportInfo toAirportInfoLocal(AirportInfoDTO airportInfoDTO);

    List<AirportInfo> toListAirportInfoLocal(List<AirportInfoDTO> airportInfoDTOList);
}
