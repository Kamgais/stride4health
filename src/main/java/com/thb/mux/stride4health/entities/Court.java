package com.thb.mux.stride4health.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Court {

    //Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "place")
    private String place;

    //@JsonIgnore // added
    @Setter
    @OneToMany(mappedBy = "court")
    private List<UserEntity> users;

}
