package com.sesame.TalentUp.repository;

import com.sesame.TalentUp.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {
}
