package com.thb.mux.stride4health.controllers;

import com.thb.mux.stride4health.entities.Court;
import com.thb.mux.stride4health.entities.UserEntity;
import com.thb.mux.stride4health.services.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticController {
    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/userRanking")
    public ResponseEntity<List<UserEntity>> getUserRanking(){
        return new ResponseEntity<>(statisticService.getUserRanking(), HttpStatus.OK);
    }

    @GetMapping("/courtRanking")
    public ResponseEntity<List<Court>> getCourtRanking(){
        return new ResponseEntity<>(statisticService.getCourtRanking(), HttpStatus.OK);
    }
}
