package com.carignano.nasa_exercise.controller;

import com.carignano.nasa_exercise.constants.ProjectConstants;
import com.carignano.nasa_exercise.dto.local.AsteroidPath;
import com.carignano.nasa_exercise.service.asteroid.IAsteroidService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequestMapping("api/asteroids")
@RequiredArgsConstructor
public class AsteroidApiController {

    private final IAsteroidService asteroidService;

    @GetMapping("{asteroidId}/paths")
    public ResponseEntity<List<AsteroidPath>> getAsteroidPaths(@PathVariable @Positive@NotNull Long asteroidId, @RequestParam(required = false) @DateTimeFormat(pattern = ProjectConstants.DateFormat.STANDARD_DATE_FORMAT) LocalDate fromDate, @RequestParam(required = false) @DateTimeFormat(pattern = ProjectConstants.DateFormat.STANDARD_DATE_FORMAT) LocalDate toDate){
        toDate = toDate == null ? LocalDate.now() : toDate;
        fromDate = fromDate == null ? LocalDate.now().minusYears(100) : fromDate;

        List<AsteroidPath> ret = asteroidService.getAsteroidPaths(asteroidId, fromDate, toDate);

        return ResponseEntity.ok(ret);
    }

}
