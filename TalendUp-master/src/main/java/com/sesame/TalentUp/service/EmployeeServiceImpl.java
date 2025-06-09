package com.sesame.TalentUp.service;

import com.sesame.TalentUp.entity.Competence;
import com.sesame.TalentUp.entity.Employee;
import com.sesame.TalentUp.entity.Inscription;
import com.sesame.TalentUp.repository.ICompetenceRepository;
import com.sesame.TalentUp.repository.IEmployeeRepository;
import com.sesame.TalentUp.repository.IInscriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeServiceImpl implements IEmployeeService{
    @Autowired
    ICompetenceRepository competenceRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IInscriptionRepository inscriptionRepository;

    @Override
    public Employee registerEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployeeProfile(int id, Employee updatedData) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existing.setNom(updatedData.getNom());
        existing.setPrenom(updatedData.getPrenom());
        existing.setEmail(updatedData.getEmail());
        existing.setTelephone(updatedData.getTelephone());
        existing.setPassword(updatedData.getPassword());

        // Clear existing competences to remove outdated ones (orphanRemoval = true will delete them)
        existing.getCompetences().clear();

        // Add new competences with proper back-reference
        if (updatedData.getCompetences() != null) {
            for (Competence competence : updatedData.getCompetences()) {
                competence.setEmployer(existing);
                existing.getCompetences().add(competence);
            }
        }

        return employeeRepository.save(existing);
    }




    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public List<Inscription> getEmployeeInscriptions(int id) {
        Employee emp = getEmployeeById(id);
        return emp.getInscriptions();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
    @Override
    @Transactional
    public Employee registerEmployeeWithCompetences(Employee employee) {
        // Extraire les compétences
        List<Competence> competences = employee.getCompetences();
        employee.setCompetences(null); // éviter cascade inutile

        // Sauvegarder l'employé d'abord
        Employee savedEmployee = employeeRepository.save(employee);

        // Associer et enregistrer les compétences
        if (competences != null) {
            for (Competence competence : competences) {
                competence.setEmployer(savedEmployee);
            }
            competenceRepository.saveAll(competences);
            savedEmployee.setCompetences(competences); // optionnel : mise à jour en mémoire
        }

        return savedEmployee;
    }

    @Override
    public Employee login(String email, String password) {
        return employeeRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    }
    public long getTotalEmployees() {
        return employeeRepository.count();
    }

}



