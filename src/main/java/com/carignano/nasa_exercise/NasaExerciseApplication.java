package com.carignano.nasa_exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class NasaExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(NasaExerciseApplication.class, args);
	}

}
