package com.carignano.nasa_exercise.controller;

import com.carignano.nasa_exercise.constants.ProjectConstants;
import com.carignano.nasa_exercise.dto.local.StationInfo;
import com.carignano.nasa_exercise.service.awc.airport.IAirportService;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequestMapping("api/airports")
public class AirportController {

    private final IAirportService airportService;

    public AirportController(IAirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping("{airportId}/stations")
    public ResponseEntity<List<StationInfo>> getAsteroidPaths(@PathVariable @NotNull String airportId, @RequestParam(required = false) Double closestBy){
        closestBy = closestBy == null ? 0 : closestBy;

        List<StationInfo> ret = airportService.getClosestByStations(airportId,closestBy);

        return ResponseEntity.ok(ret);
    }
}
