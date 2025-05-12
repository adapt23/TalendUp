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
    private int id;

    private String titre;
    private String description;
    private String duree;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    @OneToOne(mappedBy = "formation")
    private Competence competence;

    @OneToOne
    private Inscription inscription;

    @OneToOne
    private Certification certification;

    @OneToMany(mappedBy = "formation")
    private List<Evaluation> evaluations;

    // ✅ Add this field to fix your issue
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employee employer;

    // ✅ Optional custom setter
    public void setEmployer(Employee employer) {
        this.employer = employer;
    }

}
