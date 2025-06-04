package com.sesame.TalentUp.repository;

import com.sesame.TalentUp.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface IEmployeeRepository  extends CrudRepository<Employee,Integer> {
}
