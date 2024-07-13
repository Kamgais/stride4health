package com.thb.mux.stride4health.services;

import com.thb.mux.stride4health.dtos.CourtDTO;
import com.thb.mux.stride4health.dtos.UserViewDTO;
import com.thb.mux.stride4health.entities.Court;
import com.thb.mux.stride4health.entities.TrainingsDay;
import com.thb.mux.stride4health.entities.Profile;
import com.thb.mux.stride4health.repositories.ICourtRepository;
import com.thb.mux.stride4health.repositories.ITrainingsDay;
import com.thb.mux.stride4health.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticService {
    private final IUserRepository userEntityRepository;
    private final ICourtRepository courtRepository;
    private final ITrainingsDay trainingsDayRepository;

    public StatisticService(IUserRepository userEntityRepository, ICourtRepository courtRepository, ITrainingsDay trainingsDayRepository) {
        this.userEntityRepository = userEntityRepository;
        this.courtRepository = courtRepository;
        this.trainingsDayRepository = trainingsDayRepository;
    }

    public List<UserViewDTO> getUserRanking() {
        List<Profile> users = ( List<Profile>) userEntityRepository.findAll();
        return users.stream()
                .map(user -> {
                    Long totalSteps = user.getTrainingsDays().stream()
                            .mapToLong(TrainingsDay::getSteps)
                            .sum();

                    return UserViewDTO.builder()
                            .id(user.getId())
                            .email(user.getEmail())
                            .firstname(user.getFirstname())
                            .lastname(user.getLastname())
                            .password(user.getPassword())
                            .dailyGoal(user.getDailyGoal())
                            .court(user.getCourt().getPlace()) // Assuming Court has a place field
                            .totalSteps(totalSteps)
                            .build();
                })
                .sorted(Comparator.comparingLong(UserViewDTO::getTotalSteps).reversed())
                .collect(Collectors.toList());
    }

    public List<UserViewDTO> getUserRankingByCourt(Long userId) {
        // Fetch the user to get their court ID
        Profile currentUser = userEntityRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Long currentCourtId = currentUser.getCourt().getId();

        // Fetch users in the same court
        List<Profile> usersInCourt = userEntityRepository.findByCourtId(currentCourtId); // Assuming repository method to find by courtId

        return usersInCourt.stream()
                .map(user -> {
                    Long totalSteps = user.getTrainingsDays().stream()
                            .mapToLong(TrainingsDay::getSteps)
                            .sum();

                    return UserViewDTO.builder()
                            .id(user.getId())
                            .email(user.getEmail())
                            .firstname(user.getFirstname())
                            .lastname(user.getLastname())
                            .password(user.getPassword())
                            .dailyGoal(user.getDailyGoal())
                            .court(user.getCourt().getPlace()) // Assuming Court has a place field
                            .totalSteps(totalSteps)
                            .build();
                })
                .sorted(Comparator.comparingLong(UserViewDTO::getTotalSteps).reversed())
                .collect(Collectors.toList());
    }

    public List<CourtDTO> getCourtRanking() {
        List<TrainingsDay> trainingsDays = (List<TrainingsDay>) trainingsDayRepository.findAll();
        Map<Long, Long> courtStepsMap = new HashMap<>();

        for (TrainingsDay td : trainingsDays) {
            Long courtId = td.getUser().getCourt().getId();
            Long steps = td.getSteps();
            courtStepsMap.put(courtId, courtStepsMap.getOrDefault(courtId, 0L) + steps);
        }

        List<Court> courts = (List<Court>) courtRepository.findAll();
        courts.sort(Comparator.comparingLong(c -> courtStepsMap.getOrDefault(c.getId(), 0L)));

        return courts.stream()
                .map(court -> CourtDTO.builder()
                        .id(court.getId())
                        .place(court.getPlace())
                        .totalSteps(courtStepsMap.getOrDefault(court.getId(), 0L))
                        .build())
                .collect(Collectors.toList());
    }
}
