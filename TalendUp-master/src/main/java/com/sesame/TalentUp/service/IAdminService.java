package com.sesame.TalentUp.service;

import com.sesame.TalentUp.entity.Admin;
import com.sesame.TalentUp.entity.Employee;
import com.sesame.TalentUp.entity.Formation;

import java.util.List;

public interface IAdminService {
    Admin registerAdmin(Admin admin);
    Admin updateAdminProfile(int id, Admin updatedData);
    Admin getAdminById(int id);
    List<Admin> getAllAdmins();
    void deleteAdmin(int id);
    Admin login(String email, String password);
    long getTotalEmployees();
    boolean isAdmin(int userId);
}
