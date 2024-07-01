package com.thb.mux.stride4health.services;

import com.thb.mux.stride4health.entities.Court;
import com.thb.mux.stride4health.entities.TrainingsDay;
import com.thb.mux.stride4health.entities.UserEntity;
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

    public List<UserEntity> getUserRanking() {
        List<UserEntity> users = (List<UserEntity>) userEntityRepository.findAll();
        return users.stream()
                .sorted(Comparator.comparingLong(UserEntity::getTotalSteps).reversed())
                .collect(Collectors.toList());
    }

    public List<Court> getCourtRanking() {
        List<TrainingsDay> trainingsDays = (List<TrainingsDay>) trainingsDayRepository.findAll();
        Map<Long, Long> courtStepsMap = new HashMap<>();

        for (TrainingsDay td : trainingsDays) {
            Long courtId = td.getUser().getCourt().getId();
            Long steps = td.getSteps();
            courtStepsMap.put(courtId, ((courtStepsMap.getOrDefault((Object) courtId, 0L) + steps)));
        }

        List<Court> courts = (List<Court>) courtRepository.findAll();
        courts.sort(Comparator.comparingLong(c -> courtStepsMap.getOrDefault(c.getId(), 0L)));

        return courts;
    }
}
