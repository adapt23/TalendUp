package com.sesame.TalentUp.repository;

import com.sesame.TalentUp.entity.Employee;
import com.sesame.TalentUp.entity.Evaluation;
import com.sesame.TalentUp.entity.Formation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IEvaluationRepository extends CrudRepository<Evaluation,Integer> {
    List<Evaluation> findByEmployerId(int employerId);
    List<Evaluation> findByFormationId(int formationId);
    Optional<Evaluation> findByEmployerAndFormation(Employee employer, Formation formation);
    Optional<Evaluation> findByEmployerIdAndFormationId(int employerId, int formationId);





}
