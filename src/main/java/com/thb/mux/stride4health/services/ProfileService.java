package com.thb.mux.stride4health.services;

import com.thb.mux.stride4health.entities.Profile;
import com.thb.mux.stride4health.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    private final IUserRepository profileRepository;

    public ProfileService(IUserRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Optional<Profile> findById(Long id) {
        return profileRepository.findById(id);
    }

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile updateDailyGoal(Long id, Long dailyGoal) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            profile.setDailyGoal(dailyGoal);
            return profileRepository.save(profile);
        } else {
            throw new RuntimeException("Profile not found with id " + id);
        }
    }
}

