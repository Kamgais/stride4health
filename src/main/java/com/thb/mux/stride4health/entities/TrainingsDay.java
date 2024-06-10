package com.thb.mux.stride4health.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainingsDay {


    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "steps")
    private Long steps;

    @Column(name="day")
    private Date day;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;


}
