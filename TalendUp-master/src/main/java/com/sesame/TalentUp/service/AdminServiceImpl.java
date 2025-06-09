package com.sesame.TalentUp.service;

import com.sesame.TalentUp.entity.Admin;
import com.sesame.TalentUp.entity.Employee;
import com.sesame.TalentUp.entity.Formation;
import com.sesame.TalentUp.repository.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IAdminRepository adminRepository;

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public Admin registerAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin updateAdminProfile(int id, Admin updatedData) {
        Admin existing = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        existing.setNom(updatedData.getNom());
        existing.setPrenom(updatedData.getPrenom());
        existing.setEmail(updatedData.getEmail());
        existing.setPassword(updatedData.getPassword());
        return adminRepository.save(existing);
    }

    @Override
    public Admin getAdminById(int id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public void deleteAdmin(int id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Admin login(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

    }

    @Override
    public long getTotalEmployees() {
        return employeeService.getTotalEmployees();
    }
    @Override
    public boolean isAdmin(int userId) {
        // Check if an admin with given userId exists in DB
        return adminRepository.existsById(userId);
    }
}
