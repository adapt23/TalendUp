package com.sesame.TalentUp.services;

import com.sesame.TalentUp.entity.Evaluation;

public interface IInscriptionService {
    public void updateProgression(int employeId, String formationTitre, double progression);
    public double getProgression(int employeId, String formationTitre)  ;
    void genererCertificationSiEligible(int employeId, String formationTitre);
}
