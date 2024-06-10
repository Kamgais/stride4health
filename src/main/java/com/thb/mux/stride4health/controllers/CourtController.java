package com.thb.mux.stride4health.controllers;

import com.thb.mux.stride4health.entities.Court;
import com.thb.mux.stride4health.services.CourtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/courts")
public class CourtController {

    private final CourtService courtService;

    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping
    public ResponseEntity<List<Court>> getAllCourts() {
        List<Court> courts = courtService.findAll();
        return new ResponseEntity<>(courts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Court> getCourtById(@PathVariable Long id) {
        Court court = courtService.findById(id);
        if (court != null) {
            return new ResponseEntity<>(court, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Court> createCourt(@RequestBody Court court) {
        Court createdCourt = courtService.save(court);
        return new ResponseEntity<>(createdCourt, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourt(@PathVariable Long id) {
        courtService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

