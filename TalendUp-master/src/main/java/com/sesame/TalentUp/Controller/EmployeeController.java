package com.sesame.TalentUp.Controller;

import com.sesame.TalentUp.entity.Admin;
import com.sesame.TalentUp.entity.Employee;
import com.sesame.TalentUp.entity.Inscription;
import com.sesame.TalentUp.repository.IAdminRepository;
import com.sesame.TalentUp.repository.IEmployeeRepository;
import com.sesame.TalentUp.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IEmployeeRepository employeeRepo;

    @Autowired
    private IAdminRepository adminRepo;

    // Register new employee
    @PostMapping("/register")
    public Employee register(@RequestBody Employee employee) {
        System.out.println("Requête reçue : " + employee);
        return employeeService.registerEmployeeWithCompetences(employee);
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
    @GetMapping("/getallemplyee")
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

    // Unified login for Admin and Employee
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Try admin first
        Admin admin = adminRepo.findByEmail(email);
        if (admin != null && admin.getPassword().equals(password)) {
            return ResponseEntity.ok(Map.of(
                    "id", admin.getId(),
                    "email", admin.getEmail(),
                    "role", "ADMIN"
            ));
        }

        // Then try employee
        Optional<Employee> employeeOpt = employeeRepo.findByEmail(email); // ✅ FIXED

        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            if (employee.getPassword().equals(password)) {
                return ResponseEntity.ok(Map.of(
                        "id", employee.getId(),
                        "email", employee.getEmail(),
                        "role", "USER"
                ));
            }
        }

        // If neither matched
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid credentials"));
    }



}
