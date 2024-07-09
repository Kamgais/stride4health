package com.thb.mux.stride4health.controllers;
import com.thb.mux.stride4health.entities.TrainingsDay;
import com.thb.mux.stride4health.entities.UserEntity;
import com.thb.mux.stride4health.services.TrainingsDayService;
import com.thb.mux.stride4health.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final TrainingsDayService trainingsDayService;

    public UserController(UserService userService, TrainingsDayService trainingsDayService) {
        this.userService = userService;
        this.trainingsDayService = trainingsDayService;
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        UserEntity user = userService.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        UserEntity createdUser = userService.save(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{userId}/target")
    public ResponseEntity<TrainingsDay> updateDayTargetForUser(@PathVariable Long userId, @RequestParam Long dayTarget) {
        TrainingsDay updatedDayTarget = trainingsDayService.updateDayTargetForUser(userId, dayTarget);
        if (updatedDayTarget != null) {
            return new ResponseEntity<>(updatedDayTarget, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}