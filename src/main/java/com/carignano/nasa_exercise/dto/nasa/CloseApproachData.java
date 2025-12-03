package com.carignano.nasa_exercise.dto.nasa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloseApproachData {

    private LocalDate close_approach_date;
    @JsonFormat(pattern = "yyyy-MMM-dd HH:mm", locale = "en")
    private LocalDateTime close_approach_date_full;
    private Number epoch_date_close_approach;
    private String orbiting_body;

}
