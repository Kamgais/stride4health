package com.thb.mux.stride4health.dtos;


import lombok.Data;

import java.util.Date;

@Data
public class CreateTrainingsDayDTO {
    private Date day;
    private Long steps;
    private Long userId;
}
