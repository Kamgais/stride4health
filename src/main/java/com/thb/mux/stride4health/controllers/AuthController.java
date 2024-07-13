package com.thb.mux.stride4health.controllers;
import com.thb.mux.stride4health.dtos.CreateProfileDTO;
import com.thb.mux.stride4health.dtos.UserViewDTO;
import com.thb.mux.stride4health.entities.Profile;
import com.thb.mux.stride4health.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserViewDTO> register(@RequestBody CreateProfileDTO user) {
        try {
            Profile registeredUser = authService.register(user);
            UserViewDTO toReturnUser =  UserViewDTO.builder()
                    .id(registeredUser.getId())
                    .email(registeredUser.getEmail())
                    .firstname(registeredUser.getFirstname())
                    .lastname(registeredUser.getLastname())
                    .password(registeredUser.getPassword())
                    .dailyGoal(registeredUser.getDailyGoal())
                    .court(registeredUser.getCourt().getPlace())
                    .build();
            return new ResponseEntity<>(toReturnUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            System.out.print(e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserViewDTO> login(@RequestParam String username, @RequestParam String password) {
        try {
            Profile user = authService.login(username, password);
            UserViewDTO toReturnUser = UserViewDTO.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .password(user.getPassword())
                    .dailyGoal(user.getDailyGoal())
                    .court(user.getCourt().getPlace())
                    .build();
            return new ResponseEntity<>(toReturnUser, HttpStatus.OK);
        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}

