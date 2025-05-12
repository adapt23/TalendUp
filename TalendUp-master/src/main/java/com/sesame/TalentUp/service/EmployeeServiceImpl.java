package com.sesame.TalentUp.service;

import com.sesame.TalentUp.entity.Competence;
import com.sesame.TalentUp.entity.Employee;
import com.sesame.TalentUp.entity.Inscription;
import com.sesame.TalentUp.repository.IEmployeeRepository;
import com.sesame.TalentUp.repository.IInscriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements IEmployeeService{

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
        Employee existing = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        existing.setNom(updatedData.getNom());
        existing.setPrenom(updatedData.getPrenom());
        existing.setEmail(updatedData.getEmail());
        existing.setPassword(updatedData.getPassword());
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

    @Transactional
    public Employee registerEmployeeWithCompetences(Employee employee) {
        // Save the employee first
        Employee savedEmployee = employeeRepository.save(employee);

        // Ensure competences are set properly
        if (employee.getCompetences() != null) {
            for (Competence competence : employee.getCompetences()) {
                competence.setEmployer(savedEmployee); // Set the employer for each competence
            }
        }

        // Save the updated competences (if they are newly added or changed)
        employeeRepository.save(savedEmployee);

        // Return the employee with updated competences
        return savedEmployee;
    }


}
