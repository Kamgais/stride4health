package com.thb.mux.stride4health.services;

import com.thb.mux.stride4health.entities.Court;
import com.thb.mux.stride4health.entities.TrainingsDay;
import com.thb.mux.stride4health.entities.UserEntity;
import com.thb.mux.stride4health.repositories.ICourtRepository;
import com.thb.mux.stride4health.repositories.ITrainingsDay;
import com.thb.mux.stride4health.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService  {

    private final IUserRepository userRepository;

    private final ICourtRepository courtRepository;
    private final ITrainingsDay trainingsDayRepository;

    public UserService(IUserRepository userRepository, ICourtRepository courtRepository, ITrainingsDay trainingsDayRepository) {
        this.userRepository = userRepository;
        this.courtRepository = courtRepository;
        this.trainingsDayRepository = trainingsDayRepository;
    }

    public List<UserEntity> findAll() {
        return (List<UserEntity>) userRepository.findAll();
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity save(UserEntity user) {
        //return userRepository.save(user);
        // Check and set Court entity
        if (user.getCourt() != null) {
            Court court = courtRepository.findById(user.getCourt().getId()).orElse(null);
            user.setCourt(court);
        }

        // Check and set TrainingsDay entities
        if (user.getTrainingsDays() != null) {
            List<TrainingsDay> trainingsDays = user.getTrainingsDays().stream()
                    .map(trainingDay -> trainingsDayRepository.findById(trainingDay.getId()).orElse(null))
                    .collect(Collectors.toList());
            user.setTrainingsDays(trainingsDays);
        }

        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
