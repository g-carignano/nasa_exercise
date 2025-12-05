package com.carignano.nasa_exercise.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;

@Configuration
@Slf4j
public class AwcConfig {

    @Bean
    public RestClient awcRestClient(@Value("${awc.base-url}") String baseUrl){
        return RestClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(baseUrl)
                .requestInterceptor((request, body, execution) -> {

                    log.info("Serialized body: " + new String(body, StandardCharsets.UTF_8));

                    System.out.println("REQUEST URL: " + request.getURI());
                    return execution.execute(request, body);
                })
                .build();
    }

}
