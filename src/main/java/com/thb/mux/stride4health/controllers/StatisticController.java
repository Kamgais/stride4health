package com.thb.mux.stride4health.controllers;

import com.thb.mux.stride4health.dtos.CourtDTO;
import com.thb.mux.stride4health.dtos.UserViewDTO;
import com.thb.mux.stride4health.entities.Court;
import com.thb.mux.stride4health.entities.Profile;
import com.thb.mux.stride4health.services.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/statistics")
public class StatisticController {
    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/userRankingByCourt/{userId}")
    public ResponseEntity<List<UserViewDTO>> getUserRankingByCourt(@PathVariable Long userId) {
        List<UserViewDTO> userRanking = statisticService.getUserRankingByCourt(userId);
        return new ResponseEntity<>(userRanking, HttpStatus.OK);
    }

    @GetMapping("/courtRanking")
    public ResponseEntity<List<CourtDTO>> getCourtRanking(){
        return new ResponseEntity<>(statisticService.getCourtRanking(), HttpStatus.OK);
    }
}
