package com.thb.mux.stride4health.controllers;

import com.thb.mux.stride4health.entities.TrainingsDay;
import com.thb.mux.stride4health.entities.UserEntity;
import com.thb.mux.stride4health.services.TrainingsDayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trainingsDays")
public class TrainingsDayController {

    private final TrainingsDayService trainingsDayService;

    public TrainingsDayController(TrainingsDayService trainingsDayService) {
        this.trainingsDayService = trainingsDayService;
    }

    @GetMapping
    public ResponseEntity<List<TrainingsDay>> getAllTrainingsDays() {
        List<TrainingsDay> trainingsDays = trainingsDayService.findAll();
        return new ResponseEntity<>(trainingsDays, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingsDay> getTrainingsDayById(@PathVariable Long id) {
        TrainingsDay trainingsDay = trainingsDayService.findById(id);
        if (trainingsDay != null) {
            return new ResponseEntity<>(trainingsDay, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TrainingsDay> createTrainingsDay(@RequestBody TrainingsDay trainingsDay) {
        TrainingsDay createdTrainingsDay = trainingsDayService.save(trainingsDay);
        return new ResponseEntity<>(createdTrainingsDay, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingsDay(@PathVariable Long id) {
        trainingsDayService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

