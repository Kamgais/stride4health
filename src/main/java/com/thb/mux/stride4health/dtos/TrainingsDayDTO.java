package com.thb.mux.stride4health.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TrainingsDayDTO {
    private Long id;
    private Date day;
    private Long steps;
    private Long userId;
}
