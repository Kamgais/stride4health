package com.thb.mux.stride4health.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Profile {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name="daily_goal")
    private Long dailyGoal = 10000L;


    @OneToMany(mappedBy = "user")
    private List<TrainingsDay> trainingsDays;

    @ManyToOne
    @JoinColumn(name="court_id")
    private Court court;

    // Function to get the total Steps
    public Long getTotalSteps() {
        return trainingsDays.stream().mapToLong(TrainingsDay::getSteps).sum();
    }
}
