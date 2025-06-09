package com.sesame.TalentUp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String nom ;
    private String prenom ;
    private String email ;
    private String telephone ;
    private String password ;
    private int code ;
    // Relations
    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Competence> competences = new ArrayList<>();


    @OneToMany(mappedBy = "employer")
    private List<Certification> certifications;

    @OneToMany(mappedBy = "employer")
    private List<Inscription> inscriptions;

    @OneToMany(mappedBy = "employer")
    private List<Evaluation> evaluations;




}
