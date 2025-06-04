package com.sesame.TalentUp.controller;

import com.sesame.TalentUp.entity.Evaluation;
import com.sesame.TalentUp.services.IEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {
    @Autowired
    IEvaluationService evaluationService ;
    @GetMapping("/employer/{id}")
    public List<Evaluation> getByEmployer(@PathVariable int id) {
        return evaluationService.getEvaluationsParEmployer(id);
    }
    @GetMapping("/formation/{id}")
    public List<Evaluation> getByFormation(@PathVariable int id) {
        return evaluationService.getEvaluationsParFormation(id);
    }
    @GetMapping("/byEmployerAndFormation")
    public ResponseEntity<Optional<Evaluation>> getByEmployerAndFormation(@RequestParam int employerId, @RequestParam int formationId) {
        Optional<Evaluation> evaluation = evaluationService.getEvaluationByEmployerAndFormation(employerId, formationId);
        if (evaluation != null) {
            return ResponseEntity.ok(evaluation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/score")
    public ResponseEntity<Integer> getScore(
            @RequestParam int employerId,
            @RequestParam int formationId) {

        Integer score = evaluationService.getScoreFromTest(employerId, formationId);
        return ResponseEntity.ok(score);
    }

}
