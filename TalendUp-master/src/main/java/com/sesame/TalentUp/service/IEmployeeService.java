package com.sesame.TalentUp.service;

import com.sesame.TalentUp.entity.Competence;
import com.sesame.TalentUp.entity.Employee;
import com.sesame.TalentUp.entity.Inscription;

import java.util.List;

public interface IEmployeeService {
    Employee registerEmployee(Employee employee);
    Employee updateEmployeeProfile(int id, Employee updatedData);
    Employee getEmployeeById(int id);
    List<Inscription> getEmployeeInscriptions(int id);
    List<Employee> getAllEmployees();
    void deleteEmployee(int id);
    public Employee registerEmployeeWithCompetences(Employee employee);
    Employee login(String email, String password);
    long getTotalEmployees();
}
