package com.sesame.TalentUp.services.impl;

import com.sesame.TalentUp.entity.*;
import com.sesame.TalentUp.repository.*;
import com.sesame.TalentUp.services.IEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationServiceImpl implements IEvaluationService {
    @Autowired
    IEvaluationRepository evaluationRepository ;


    @Override
    public List<Evaluation> getEvaluationsParEmployer(int employerId) {
        return evaluationRepository.findByEmployerId(employerId);
    }

    @Override
    public List<Evaluation> getEvaluationsParFormation(int formationId) {
        return evaluationRepository.findByFormationId(formationId);
    }

    @Override
    public Optional<Evaluation> getEvaluationByEmployerAndFormation(int employerId, int formationId) {
        return evaluationRepository.findByEmployerIdAndFormationId(employerId, formationId);
    }

    @Override
    public Integer getScoreFromTest(int employerId, int formationId) {
        //todo: verify with the FRont
        Optional<Evaluation> evaluation = evaluationRepository.findByEmployerIdAndFormationId(employerId, formationId);

        return evaluation
                .map(Evaluation::getScore)
                .orElseThrow(() -> new RuntimeException("Aucun test trouvé pour cet employé dans cette formation"));
    }




}
