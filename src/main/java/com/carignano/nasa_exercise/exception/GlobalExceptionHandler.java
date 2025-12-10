package com.carignano.nasa_exercise.exception;

import com.carignano.nasa_exercise.dto.response.NasaAwcError;
import com.carignano.nasa_exercise.dto.response.NasaAwcResponse;
import com.carignano.nasa_exercise.exception.custom.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<NasaAwcResponse<Void>> catchEntityNotFoundException(EntityNotFoundException efe){
        NasaAwcResponse<Void> response = new NasaAwcResponse<>(null, List.of(new NasaAwcError(efe.getCodeError().toString(), efe.getMsg())));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

}
