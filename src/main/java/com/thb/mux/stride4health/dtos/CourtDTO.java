package com.thb.mux.stride4health.dtos;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourtDTO {
    private Long id;
    private String place;
    private Long totalSteps;
}
