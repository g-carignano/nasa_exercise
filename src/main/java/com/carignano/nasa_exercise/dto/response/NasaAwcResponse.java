package com.carignano.nasa_exercise.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NasaAwcResponse<T> {

    private T payload;
    private List<NasaAwcError> errorList;

}
