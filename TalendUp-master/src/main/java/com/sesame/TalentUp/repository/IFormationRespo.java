package com.sesame.TalentUp.repository;

import com.sesame.TalentUp.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFormationRespo extends JpaRepository<Formation, Integer> {
}
