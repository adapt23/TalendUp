package com.sesame.TalentUp.Controller;

import com.sesame.TalentUp.entity.Employee;
import com.sesame.TalentUp.entity.Inscription;
import com.sesame.TalentUp.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    // Register new employee
    @PostMapping("/register")
    public Employee register(@RequestBody Employee employee) {
        return employeeService.registerEmployee(employee);
    }

    // Update employee profile
    @PutMapping("/{id}/profile")
    public Employee updateProfile(@PathVariable int id, @RequestBody Employee updated) {
        return employeeService.updateEmployeeProfile(id, updated);
    }

    // Get one employee
    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    // Get all employees
    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

    // Delete employee
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

    // Get inscriptions for an employee
    @GetMapping("/{id}/inscriptions")
    public List<Inscription> getInscriptions(@PathVariable int id) {
        return employeeService.getEmployeeInscriptions(id);
    }

}
