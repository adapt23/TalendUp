package com.sesame.TalentUp.controller;

import com.sesame.TalentUp.services.IInscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inscriptionList")
public class InscriptionController {

    @Autowired
    private IInscriptionService inscriptionService;

    //  Endpoint pour mettre à jour la progression d'une formation par nom
    @PutMapping("/update-progression")
    public ResponseEntity<String> updateProgression(
            @RequestParam int employeId,
            @RequestParam String formationTitre,
            @RequestParam double progression) {
        try {
            inscriptionService.updateProgression(employeId, formationTitre, progression);
            return ResponseEntity.ok("Progression mise à jour avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //  Endpoint pour récupérer la progression par nom de formation
    @GetMapping("/progression")
    public ResponseEntity<Double> getProgression(
            @RequestParam int employeId,
            @RequestParam String formationTitre) {
        try {
            double progression = inscriptionService.getProgression(employeId, formationTitre);
            return ResponseEntity.ok(progression);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/generer")
    public ResponseEntity<String> genererCertification(
            @RequestParam int employeId,
            @RequestParam String formationTitre) {
        try {
            inscriptionService.genererCertificationSiEligible(employeId, formationTitre);
            return ResponseEntity.ok("Certification générée avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
