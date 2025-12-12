package com.carignano.nasa_exercise.service.asteroid;

import com.carignano.nasa_exercise.dto.local.AsteroidPath;
import com.carignano.nasa_exercise.dto.nasa.AsteroidInfoNasa;
import com.carignano.nasa_exercise.dto.nasa.CloseApproachData;
import com.carignano.nasa_exercise.exception.custom.InvalidDataInputException;
import com.carignano.nasa_exercise.service.client.nasa.NasaClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AsteroidService implements IAsteroidService{

    private final NasaClientService nasaClientService;

    @Override
    @Cacheable(cacheNames = "asteroidPaths")
    public List<AsteroidPath> getAsteroidPaths(Long asteroidId, LocalDate fromDate, LocalDate toDate) {

        if(fromDate.isAfter(toDate))
            throw new InvalidDataInputException("toDate is smaller then fromDate");

        log.info("Filtering asteroidId: {} paths fromDate: {} toDate: {}",asteroidId, fromDate, toDate);

        AsteroidInfoNasa asteroidInfoNasa = nasaClientService.getAsteroidInfo(asteroidId);
        List<AsteroidPath> ret;

        ret = filterAsteroidPaths(asteroidInfoNasa, fromDate, toDate);

        return ret;
    }

    /// This method works only if the data is sorted by close_approach_date
    private List<AsteroidPath> filterAsteroidPaths(AsteroidInfoNasa asteroidInfo, LocalDate fromDate, LocalDate toDate){
        List<AsteroidPath> ret = new ArrayList<>();
        List<CloseApproachData> cadList = asteroidInfo.getClose_approach_data();
        CloseApproachData cadTemp = null;
        int trimIndex = 0;

        //Filer out the orbits where the fromDate is > than the close_approach_date
        for(CloseApproachData cad : cadList){
            if(cad.getClose_approach_date().isBefore(fromDate))
                trimIndex++;
            else
                break;
        }

        cadList = cadList.subList(trimIndex, cadList.size());

        for(CloseApproachData cad : cadList){
            if(cadTemp == null){
                cadTemp = cad;
                continue;
            }

            //If the current close_approach_date is bigget than the toDate then we must stop
            if(!cad.getClose_approach_date().isBefore(toDate))
                break;

            if(!cadTemp.getOrbiting_body().equals(cad.getOrbiting_body())){
                ret.add(new AsteroidPath(cadTemp.getOrbiting_body(), cad.getOrbiting_body(), cadTemp.getClose_approach_date(), cad.getClose_approach_date()));
                cadTemp = cad;
            }

        }

        return ret;
    }
}
