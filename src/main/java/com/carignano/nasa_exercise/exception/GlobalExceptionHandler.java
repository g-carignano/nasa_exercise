package com.carignano.nasa_exercise.exception;

import com.carignano.nasa_exercise.dto.response.NasaAwcError;
import com.carignano.nasa_exercise.dto.response.NasaAwcResponse;
import com.carignano.nasa_exercise.exception.custom.EntityNotFoundException;
import com.carignano.nasa_exercise.exception.custom.InvalidDataInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<NasaAwcResponse<Void>> catchEntityNotFoundException(EntityNotFoundException efe){
        NasaAwcResponse<Void> response = new NasaAwcResponse<>(null, List.of(new NasaAwcError(efe.getCodeError().toString(), efe.getMsg())));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InvalidDataInputException.class)
    public ResponseEntity<NasaAwcResponse<Void>> catchInvalidDataInputException(InvalidDataInputException idie){
        NasaAwcResponse<Void> response = new NasaAwcResponse<>(null, List.of(new NasaAwcError(idie.getCodeError().toString(), idie.getMsg())));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<NasaAwcResponse<Void>> catchNotManagedException(Exception ex){

        NasaAwcResponse<Void> response = new NasaAwcResponse<>(null, List.of(new NasaAwcError("000", "Internal Server Error")));

        log.error("Unhandled exception error: ",ex);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
