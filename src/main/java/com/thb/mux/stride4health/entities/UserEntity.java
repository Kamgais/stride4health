package com.thb.mux.stride4health.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;


    @OneToMany(mappedBy = "user")
    private List<TrainingsDay> trainingsDays;

    @ManyToOne
    @JoinColumn(name="court_id")
    private Court court;
}
