package com.thb.mux.stride4health.services;
import com.thb.mux.stride4health.entities.UserEntity;
import com.thb.mux.stride4health.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {


    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAll() {
        return (List<UserEntity>) userRepository.findAll();
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
