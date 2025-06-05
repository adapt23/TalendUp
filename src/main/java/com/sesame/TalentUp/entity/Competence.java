package com.sesame.TalentUp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//todo: spring boot, java
public class   Competence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String nom ;
    private String description  ;
    @Enumerated(EnumType.STRING)
    private Niveau niveau ;
    @Enumerated(EnumType.STRING)
    private Langue langue ;
    @OneToOne
    Formation formation ;
    @ManyToMany(mappedBy = "competences")
    List<Employee> employeeList;
}
