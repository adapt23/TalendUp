package com.sesame.TalentUp.repository;

import com.sesame.TalentUp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmailAndPassword(String email, String password);

    Optional<Employee> findByEmail(String email);

}
