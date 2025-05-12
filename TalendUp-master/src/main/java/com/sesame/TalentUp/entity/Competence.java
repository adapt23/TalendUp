package com.sesame.TalentUp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Competence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String nom ;
    private String description  ;
    @Enumerated(EnumType.STRING)
    private Niveau niveau ;
    @OneToOne
    Formation formation ;
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employee employer;


}
