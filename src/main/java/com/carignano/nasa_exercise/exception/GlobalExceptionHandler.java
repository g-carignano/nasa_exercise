package com.carignano.nasa_exercise.exception;

import com.carignano.nasa_exercise.dto.response.NasaAwcError;
import com.carignano.nasa_exercise.dto.response.NasaAwcResponse;
import com.carignano.nasa_exercise.exception.custom.EntityNotFoundException;
import com.carignano.nasa_exercise.exception.custom.InvalidDataInputException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
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


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<NasaAwcResponse<Void>> catchMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException matme){
        NasaAwcResponse<Void> response = new NasaAwcResponse<>(null, List.of(new NasaAwcError("004", String.format("Invalid input, input name: %s",matme.getName()))));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<NasaAwcResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException manve){
        NasaAwcResponse<Void> response = new NasaAwcResponse<>(null, List.of(new NasaAwcError("005", String.format("Invalid input, input name: %s",manve.getBody()))));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<NasaAwcResponse<Void>> catchConstraintViolationException(ConstraintViolationException cve){
        NasaAwcResponse<Void> response = new NasaAwcResponse<>(null, null);
        List<NasaAwcError> errorList = new ArrayList<>();

        cve.getConstraintViolations().forEach(cv -> errorList.add(new NasaAwcError("006", String.format("Invalid input, input name: %s %s",cv.getPropertyPath(), cv.getInvalidValue()))) );
        response.setErrorList(errorList);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<NasaAwcResponse<Void>> catchNotManagedException(Exception ex){

        NasaAwcResponse<Void> response = new NasaAwcResponse<>(null, List.of(new NasaAwcError("000", "Internal Server Error")));

        log.error("Unhandled exception error: ",ex);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
