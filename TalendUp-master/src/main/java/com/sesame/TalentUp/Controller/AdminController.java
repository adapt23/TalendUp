package com.sesame.TalentUp.Controller;

import com.sesame.TalentUp.entity.Admin;
import com.sesame.TalentUp.service.IAdminService;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        try {
            String email = loginData.get("email");
            String password = loginData.get("password");
            Admin admin = adminService.login(email, password);
            return ResponseEntity.ok(admin);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/stats/totalEmployees")
    public ResponseEntity<Long> getTotalEmployees() {
        long total = adminService.getTotalEmployees();
        return ResponseEntity.ok(total);
    }
    // Delete admin
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        adminService.deleteAdmin(id);
    }
    @GetMapping("/checkAdmin")
    public ResponseEntity<?> checkAdmin(@RequestParam int userId) {
        boolean isAdmin = adminService.isAdmin(userId);
        return ResponseEntity.ok(Map.of("isAdmin", isAdmin));
    }

}
