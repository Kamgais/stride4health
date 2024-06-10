package com.thb.mux.stride4health.services;


import com.thb.mux.stride4health.entities.Court;
import com.thb.mux.stride4health.repositories.ICourtRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourtService {

    private final ICourtRepository courtRepository;

    public CourtService(ICourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    public List<Court> findAll() {
        return (List<Court>) courtRepository.findAll();
    }

    public Court findById (Long id) {
        return courtRepository.findById(id).orElse(null);
    }

    public Court save(Court court) {
        return courtRepository.save(court);
    }

    public void deleteById(Long id) {
        courtRepository.deleteById(id);
    }
}
