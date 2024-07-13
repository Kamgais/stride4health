package com.thb.mux.stride4health.services;

import com.thb.mux.stride4health.dtos.CreateTrainingsDayDTO;
import com.thb.mux.stride4health.dtos.TrainingsDayDTO;
import com.thb.mux.stride4health.dtos.UpdateTrainingsDayDTO;
import com.thb.mux.stride4health.entities.Profile;
import com.thb.mux.stride4health.entities.TrainingsDay;
import com.thb.mux.stride4health.repositories.ITrainingsDay;
import com.thb.mux.stride4health.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    public TrainingsDay save(CreateTrainingsDayDTO trainingsDay) {
        System.out.print(trainingsDay.getDay().toString());
        Optional<Profile> user = userRepository.findById(trainingsDay.getUserId());
        if(user.isEmpty()) {
            return null;
        }
        TrainingsDay trainingsDay1 = new TrainingsDay();
        trainingsDay1.setDay(trainingsDay.getDay());
        trainingsDay1.setSteps(trainingsDay.getSteps());
        trainingsDay1.setUser(user.get());
        return trainingsDayRepository.save(trainingsDay1);
    }


    public TrainingsDay updateTrainingsDay(UpdateTrainingsDayDTO dto) {
        Optional<TrainingsDay> optionalTrainingsDay = trainingsDayRepository.findById(dto.getId());
        if (!optionalTrainingsDay.isPresent()) {
            throw new RuntimeException("TrainingsDay not found");
        }

        TrainingsDay trainingsDay = optionalTrainingsDay.get();
        trainingsDay.setSteps(dto.getSteps());
        trainingsDay.setDay(dto.getDay());

        return trainingsDayRepository.save(trainingsDay);
    }

    public void deleteById(Long id) {
        trainingsDayRepository.deleteById(id);
    }

    public Optional<TrainingsDay> getStepsForToday(Long userId) {
        Date today = resetTime(new Date());
        System.out.print(today);
        return trainingsDayRepository.findByUserIdAndDay(userId, today);
    }

    public List<TrainingsDay> getAllTrainingsDaysForUser(Long userId) {
        return trainingsDayRepository.findByUserId(userId);
    }

    public List<TrainingsDay> getTrainingsDaysForCurrentWeek(Long userId) {
        Date[] weekBounds = getStartAndEndOfWeek(new Date());
        return trainingsDayRepository.findByUserIdAndWeek(userId, weekBounds[0], weekBounds[1]);
    }



    private Date[] getStartAndEndOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Set the start of the week (assuming the week starts on Monday)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY,2);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startOfWeek = calendar.getTime();

        // Set the end of the week
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        Date endOfWeek = calendar.getTime();

        return new Date[]{startOfWeek, endOfWeek};
    }

    private Date resetTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}

