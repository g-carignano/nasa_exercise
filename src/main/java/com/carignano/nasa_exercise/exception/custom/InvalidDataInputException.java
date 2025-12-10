package com.carignano.nasa_exercise.exception.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidDataInputException extends RuntimeException{
    private final static Integer CODE_ERROR = 3;
    private String msg;
    private Integer codeError;

    public InvalidDataInputException(String msg) {
        super();
        this.codeError = CODE_ERROR;
        this.msg = msg;
    }
}
