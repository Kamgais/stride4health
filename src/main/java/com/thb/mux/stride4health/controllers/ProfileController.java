package com.thb.mux.stride4health.controllers;

import com.thb.mux.stride4health.dtos.UserViewDTO;
import com.thb.mux.stride4health.entities.Profile;
import com.thb.mux.stride4health.services.ProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PutMapping("/{id}/daily-goal")
    public UserViewDTO updateDailyGoal(@PathVariable Long id, @RequestParam Long dailyGoal) {
        Profile user = profileService.updateDailyGoal(id, dailyGoal);
        UserViewDTO toReturnUser = UserViewDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .password(user.getPassword())
                .dailyGoal(user.getDailyGoal())
                .court(user.getCourt().getPlace())
                .build();
        return toReturnUser;
    }
}

