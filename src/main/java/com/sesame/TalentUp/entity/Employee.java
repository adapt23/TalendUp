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
public class Employee  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String nom ;
    private String prenom ;
    private String email ;
    private String password ;
    private String Telephone ;
    private int code ;

    @OneToMany (mappedBy = "employer")
    List<Evaluation> evaluations;
    @OneToMany(mappedBy = "employer")
    List<Certification> certifications;
    @OneToMany(mappedBy = "employer")
    List<Inscription> inscriptions;
    @ManyToMany
    List<Competence> competences;

}
