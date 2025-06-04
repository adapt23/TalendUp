package com.sesame.TalentUp.services.impl;

import com.sesame.TalentUp.entity.*;
import com.sesame.TalentUp.repository.*;
import com.sesame.TalentUp.services.IInscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InscriptionServiceImpl implements IInscriptionService {

    @Autowired
    IEvaluationRepository evaluationRepository ;
    @Autowired
    ICertifactionRepository certifactionRepository ;
    @Autowired
    IInscriptionRepository iInscriptionRepository ;
    @Autowired
    IFormationRepository formationRepository ;
    @Autowired
    IEmployeeRepository employeeRepository ;

    @Override
    public void updateProgression(int employeId, String formationTitre, double progression) {
        Inscription inscription = iInscriptionRepository.findByEmployerIdAndFormationTitre(employeId, formationTitre);

        if (inscription != null) {
            inscription.setProgression(progression);
            iInscriptionRepository.save(inscription);
        } else {
            throw new RuntimeException("Inscription non trouvée pour cet employé et cette formation.");
        }
    }

    @Override
    public double getProgression(int employeId, String formationTitre) {
        Inscription inscription = iInscriptionRepository.findByEmployerIdAndFormationTitre(employeId, formationTitre);

        if (inscription != null) {
            return inscription.getProgression();
        } else {
            throw new RuntimeException("Inscription non trouvée pour cet employé et cette formation.");
        }
    }

    @Override
    public void genererCertificationSiEligible(int employeId, String formationTitre) {
        Employee employee = employeeRepository.findById(employeId)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));

        Formation formation = formationRepository
                .findByTitre(formationTitre)
                .orElseThrow(() -> new RuntimeException("Formation non trouvée"));

        // Vérifier la progression
        Inscription inscription = iInscriptionRepository.findByEmployerAndFormation(employee, formation)
                .orElseThrow(() -> new RuntimeException("Inscription non trouvée"));

        if (inscription.getProgression() < 100.0) {
            throw new RuntimeException("Formation non encore terminée");
        }

        // Vérifier l’évaluation
        Evaluation evaluation = evaluationRepository
                .findByEmployerAndFormation(employee, formation)
                .orElseThrow(() -> new RuntimeException("Évaluation non trouvée"));

        if (evaluation.getScore() < 60) {
            throw new RuntimeException("Score insuffisant pour obtenir une certification");
        }

        // Vérifier si une certification existe déjà
        boolean dejaCertifie = certifactionRepository
                .existsByEmployerAndFormation(employee, formation);

        if (dejaCertifie) {
            throw new RuntimeException("Certification déjà attribuée");
        }

        // Créer et enregistrer la certification
        Certification certification = new Certification();
        certification.setNom("Certification - " + formation.getTitre());
        certification.setDateObtention(new Date());
        certification.setEmployer(employee);
        certification.setFormation(formation);
        certifactionRepository.save(certification);
    }



}
