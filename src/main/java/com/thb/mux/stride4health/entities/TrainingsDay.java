package com.thb.mux.stride4health.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainingsDay {

    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "steps")
    private Long steps;

    @Setter
    @Column(name="day")
    private Date date;

    @Setter
    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

}
