package com.sesame.TalentUp.repository;

import com.sesame.TalentUp.entity.Admin;
import com.sesame.TalentUp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByEmail(String email);
    Optional<Admin> findByEmailAndPassword(String email, String password);

}
