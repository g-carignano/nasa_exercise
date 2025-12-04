package com.carignano.nasa_exercise.controller;

import com.carignano.nasa_exercise.dto.local.AirportInfo;
import com.carignano.nasa_exercise.dto.local.StationInfo;
import com.carignano.nasa_exercise.service.awc.station.IStationService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("api/stations")
public class StationController {

    private final IStationService stationService;

    public StationController(IStationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("{stationId}/airports")
    public ResponseEntity<List<AirportInfo>> getAsteroidPaths(@PathVariable @NotNull String stationId, @RequestParam(required = false) Double closestBy){
        closestBy = closestBy == null ? 0 : closestBy;

        List<AirportInfo> ret = stationService.getClosestByStations(stationId,closestBy);

        return ResponseEntity.ok(ret);
    }
}
