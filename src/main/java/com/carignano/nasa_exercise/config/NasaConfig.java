package com.carignano.nasa_exercise.config;

import com.carignano.nasa_exercise.helper.ClientHttpRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Configuration
public class NasaConfig {

    private static final Logger log = LoggerFactory.getLogger(NasaConfig.class);

    @Bean
    public RestClient restClient(@Value("${nasa.base-url}") String baseUrl, @Value("${nasa.api-key}") String apiKey){
        return RestClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(baseUrl)
                .defaultHeader("Api-Key", apiKey)
                .requestInterceptor((request, body, execution) -> {

                    URI uriWithKey = UriComponentsBuilder
                            .fromUri(request.getURI())
                            .queryParam("api_key", apiKey)   // <-- your default parameter
                            .build(true)
                            .toUri();

                    log.info("Serialized body: " + new String(body, StandardCharsets.UTF_8));

                    request = ClientHttpRequestWrapper.replaceUri(request, uriWithKey);
                    System.out.println("REQUEST URL: " + request.getURI());
                    return execution.execute(request, body);
                })
                .build();
    }

}
