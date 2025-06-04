package com.sesame.TalentUp.services;

import com.sesame.TalentUp.entity.Evaluation;

import java.util.List;
import java.util.Optional;

public interface IEvaluationService {
    public List<Evaluation> getEvaluationsParEmployer(int employerId);
    public List<Evaluation> getEvaluationsParFormation(int formationId) ;
    public Optional<Evaluation> getEvaluationByEmployerAndFormation(int employerId, int formationId) ;
    Integer getScoreFromTest(int employerId, int formationId);

}
