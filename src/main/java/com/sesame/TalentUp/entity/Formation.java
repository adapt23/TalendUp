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
public class Formation  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String titre ;
    private String description ;
    private String duree ;
    @Enumerated(EnumType.STRING)
    private Categorie categorie ;
    @OneToOne(mappedBy = "formation")
    Competence competence ;
    @OneToOne(mappedBy = "formation")
    Inscription inscription ;
    @OneToOne
    Certification certification ;
    @OneToMany (mappedBy = "formation")
    List<Evaluation> evaluations ;
}
