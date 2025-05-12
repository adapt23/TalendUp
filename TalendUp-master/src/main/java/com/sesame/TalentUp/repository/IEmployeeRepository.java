package com.sesame.TalentUp.repository;

import com.sesame.TalentUp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
}
