package com.sesame.TalentUp.repository;

import com.sesame.TalentUp.entity.Formation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IFormationRepository extends CrudRepository<Formation,Integer> {
    Optional<Formation> findByTitre(String titre);


}
