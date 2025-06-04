package com.sesame.TalentUp.repository;

import com.sesame.TalentUp.entity.Employee;
import com.sesame.TalentUp.entity.Formation;
import com.sesame.TalentUp.entity.Inscription;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IInscriptionRepository extends CrudRepository<Inscription,Integer> {
    Inscription findByEmployerIdAndFormationTitre(int employeId, String formationTitre);
    Optional<Inscription> findByEmployerAndFormation(Employee employer, Formation formation);


}
