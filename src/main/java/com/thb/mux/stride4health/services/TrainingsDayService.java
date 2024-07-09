package com.thb.mux.stride4health.services;

import com.thb.mux.stride4health.entities.Court;
import com.thb.mux.stride4health.entities.TrainingsDay;
import com.thb.mux.stride4health.entities.UserEntity;
import com.thb.mux.stride4health.repositories.ITrainingsDay;
import com.thb.mux.stride4health.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingsDayService {

    private final ITrainingsDay trainingsDayRepository;
    private final IUserRepository userRepository;

    public TrainingsDayService(ITrainingsDay trainingsDayRepository, IUserRepository userRepository) {
        this.trainingsDayRepository = trainingsDayRepository;
        this.userRepository = userRepository;
    }

    public List<TrainingsDay> findAll() {
        return (List<TrainingsDay>) trainingsDayRepository.findAll();
    }

    public TrainingsDay findById(Long id) {
        return trainingsDayRepository.findById(id).orElse(null);
    }

    public TrainingsDay save(TrainingsDay trainingsDay) {
        if (trainingsDay.getUser() != null) {
            UserEntity user = userRepository.findById(trainingsDay.getUser().getId()).orElse(null);
            trainingsDay.setUser(user);
        }
        return trainingsDayRepository.save(trainingsDay);
    }

    public void deleteById(Long id) {
        trainingsDayRepository.deleteById(id);
    }

    public TrainingsDay updateDayTargetForUser(Long userId, Long dayTarget) {
        List<TrainingsDay> userTrainingsDays = trainingsDayRepository.findByUserId(userId);
        if (!userTrainingsDays.isEmpty()) {
            TrainingsDay latestTrainingsDay = userTrainingsDays.get(userTrainingsDays.size() - 1); // or any logic to find the specific TrainingsDay
            latestTrainingsDay.setDay_target(dayTarget);
            return trainingsDayRepository.save(latestTrainingsDay);
        } else {
            return null;
        }
    }
}

