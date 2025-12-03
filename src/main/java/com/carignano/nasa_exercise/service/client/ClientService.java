package com.carignano.nasa_exercise.service.client;

import com.carignano.nasa_exercise.dto.nasa.AsteroidInfoNasa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ClientService implements IClientService {

    @Value("${nasaapi.asteroid.lookup}")
    private String getAsteroidInfoUrl;

    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    private final RestClient restClient;

    public ClientService(RestClient restClient){
        this.restClient = restClient;
    }

    @Override
    public AsteroidInfoNasa getAsteroidInfo(Long asteroidId) {
        log.info("Calling external api: {} with accountId: {}",getAsteroidInfoUrl, asteroidId);

        return restClient.get()
                .uri(getAsteroidInfoUrl, asteroidId)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<AsteroidInfoNasa>() {})
                .getBody();
    }

}
