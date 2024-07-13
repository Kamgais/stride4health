package com.thb.mux.stride4health.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserViewDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Long dailyGoal;
    private String court;
    private Long totalSteps;
}
