package com.thb.mux.stride4health.services;
import com.thb.mux.stride4health.dtos.CreateProfileDTO;
import com.thb.mux.stride4health.entities.Court;
import com.thb.mux.stride4health.entities.Profile;
import com.thb.mux.stride4health.repositories.ICourtRepository;
import com.thb.mux.stride4health.repositories.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final IUserRepository userRepository;

    private final ICourtRepository courtRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(IUserRepository userRepository, ICourtRepository courtRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.courtRepository = courtRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Profile register(CreateProfileDTO user) {
        // Check if user already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }
        // get the court
        Optional<Court> court = courtRepository.findByPlace(user.getCourt());
        if(court.isEmpty()) {
            return null;
        }
        Profile profile = new Profile();
        profile.setFirstname(user.getFirstname());
        profile.setLastname(user.getLastname());
        profile.setEmail(user.getEmail());
        profile.setPassword(passwordEncoder.encode(user.getPassword()));
        profile.setCourt(court.get());
        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(profile);
    }

    public Profile login(String username, String password) {
       Profile user = userRepository.findByEmail(username).orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return user;
    }
}

