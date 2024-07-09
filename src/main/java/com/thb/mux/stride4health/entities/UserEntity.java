package com.thb.mux.stride4health.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "username")
    private String username;

    @Setter
    @Column(name = "password")
    private String password;


    @Setter
    @OneToMany(mappedBy = "user")
    private List<TrainingsDay> trainingsDays;


    @Setter
    @ManyToOne
    @JoinColumn(name="court_id")
    private Court court;

    // Function to get the total Steps
    public Long getTotalSteps() {
        return trainingsDays.stream().mapToLong(TrainingsDay::getSteps).sum();
    }
}
