package com.thb.mux.stride4health.services;

import com.thb.mux.stride4health.entities.TrainingsDay;
import com.thb.mux.stride4health.repositories.ITrainingsDay;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingsDayService {

    private final ITrainingsDay trainingsDayRepository;

    public TrainingsDayService(ITrainingsDay trainingsDayRepository) {
        this.trainingsDayRepository = trainingsDayRepository;
    }

    public List<TrainingsDay> findAll() {
        return (List<TrainingsDay>) trainingsDayRepository.findAll();
    }

    public TrainingsDay findById(Long id) {
        return trainingsDayRepository.findById(id).orElse(null);
    }

    public TrainingsDay save(TrainingsDay trainingsDay) {
        return trainingsDayRepository.save(trainingsDay);
    }

    public void deleteById(Long id) {
        trainingsDayRepository.deleteById(id);
    }
}

