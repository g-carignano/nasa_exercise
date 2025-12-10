package com.carignano.nasa_exercise.exception.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException {
    private String msg;
    private Integer codeError;
}
