package com.sesame.TalentUp.repository;

import com.sesame.TalentUp.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInscriptionRepository extends JpaRepository<Inscription, Integer> {
}
