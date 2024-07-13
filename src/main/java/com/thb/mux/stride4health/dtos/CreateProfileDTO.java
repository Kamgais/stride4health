package com.thb.mux.stride4health.dtos;


import lombok.Data;

@Data
public class CreateProfileDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Long dailyGoal;
    private String court;
}
