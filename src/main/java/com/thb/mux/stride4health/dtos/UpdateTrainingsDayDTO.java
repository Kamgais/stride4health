package com.thb.mux.stride4health.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateTrainingsDayDTO {
    private Long id;
    private Long steps;
    private Date day;
}
