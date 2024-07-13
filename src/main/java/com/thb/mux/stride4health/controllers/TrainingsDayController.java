package com.thb.mux.stride4health.controllers;

import com.thb.mux.stride4health.dtos.CreateTrainingsDayDTO;
import com.thb.mux.stride4health.dtos.TrainingsDayDTO;
import com.thb.mux.stride4health.dtos.UpdateTrainingsDayDTO;
import com.thb.mux.stride4health.entities.TrainingsDay;
import com.thb.mux.stride4health.services.TrainingsDayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/trainingsdays")
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



    @PostMapping
    public ResponseEntity<TrainingsDayDTO> createTrainingsDay(@RequestBody CreateTrainingsDayDTO trainingsDay) {
        TrainingsDay createdTrainingsDay = trainingsDayService.save(trainingsDay);
        TrainingsDayDTO dto = TrainingsDayDTO.builder()
                .id(createdTrainingsDay.getId())
                .day(createdTrainingsDay.getDay())
                .steps(createdTrainingsDay.getSteps())
                .userId(createdTrainingsDay.getUser().getId())
                .build();
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingsDay(@PathVariable Long id) {
        trainingsDayService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{userId}/today")
    public TrainingsDayDTO getStepsForToday(@PathVariable Long userId) {
        Optional<TrainingsDay> trainingsDay = trainingsDayService.getStepsForToday(userId);
        if(trainingsDay.isEmpty()) {
            System.out.print("Hallo");
            return null;
        }
        TrainingsDayDTO dto = TrainingsDayDTO.builder()
                .id(trainingsDay.get().getId())
                .day(trainingsDay.get().getDay())
                .steps(trainingsDay.get().getSteps())
                .userId(trainingsDay.get().getUser().getId())
                .build();
        return dto;
    }

    @GetMapping("/{userId}")
    public List<TrainingsDayDTO> getAllTrainingsDaysForUser(@PathVariable Long userId) {
       List<TrainingsDay> trainingsDays = trainingsDayService.getAllTrainingsDaysForUser(userId);
       List<TrainingsDayDTO> dtos = trainingsDays.stream().map(e -> TrainingsDayDTO.builder()
               .id(e.getId())
               .day(e.getDay())
               .steps(e.getSteps())
               .userId(e.getUser().getId())
               .build()).collect(Collectors.toList());

       return dtos;
    }

    @GetMapping("/{userId}/current-week")
    public List<TrainingsDayDTO> getTrainingsDaysForCurrentWeek(@PathVariable Long userId) {
        List<TrainingsDay> trainingsDays = trainingsDayService.getTrainingsDaysForCurrentWeek(userId);
        List<TrainingsDayDTO> dtos = trainingsDays.stream().map(e -> TrainingsDayDTO.builder()
                .id(e.getId())
                .day(e.getDay())
                .steps(e.getSteps())
                .userId(e.getUser().getId())
                .build()).collect(Collectors.toList());

        return dtos;
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingsDayDTO> updateTrainingsDay(@PathVariable Long id, @RequestBody UpdateTrainingsDayDTO dto) {
        dto.setId(id);
        TrainingsDay updatedTrainingsDay = trainingsDayService.updateTrainingsDay(dto);
        TrainingsDayDTO responseDto = TrainingsDayDTO.builder()
                .id(updatedTrainingsDay.getId())
                .day(updatedTrainingsDay.getDay())
                .steps(updatedTrainingsDay.getSteps())
                .userId(updatedTrainingsDay.getUser().getId())
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}

