package com.sesame.TalentUp.Controller;

import com.sesame.TalentUp.entity.Admin;
import com.sesame.TalentUp.service.IAdminService;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    // Register new admin
    @PostMapping("/register")
    public Admin register(@RequestBody Admin admin) {
        return adminService.registerAdmin(admin);
    }

    // Update admin profile
    @PutMapping("/{id}/profile")
    public Admin updateProfile(@PathVariable int id, @RequestBody Admin updated) {
        return adminService.updateAdminProfile(id, updated);
    }

    // Get one admin
    @GetMapping("/{id}")
    public Admin getById(@PathVariable int id) {
        return adminService.getAdminById(id);
    }

    // Get all admins
   

    // Delete admin
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        adminService.deleteAdmin(id);
    }

}
