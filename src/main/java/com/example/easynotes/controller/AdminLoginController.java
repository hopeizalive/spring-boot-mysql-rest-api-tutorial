package com.example.easynotes.controller;

import com.example.easynotes.model.AdminLogin;
import com.example.easynotes.model.Person;
import com.example.easynotes.repository.AdminLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.easynotes.exception.ResourceNotFoundException;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class AdminLoginController {

    @Autowired
    private AdminLoginRepository adminLoginRepository;

    @GetMapping
    public List<AdminLogin> getAllAdmins() {
        return adminLoginRepository.findAll();
    }

    @PostMapping
    public AdminLogin createAdmin(@RequestBody AdminLogin adminLogin) {
        return adminLoginRepository.save(adminLogin);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminLogin> getAdminById(@PathVariable Long id) {
        AdminLogin adminLogin = adminLoginRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("AdminLogin", "adminID", id));

        return ResponseEntity.ok().body(adminLogin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminLogin> updateAdmin(@PathVariable Long id, @RequestBody AdminLogin adminDetails) {
        AdminLogin adminLogin = adminLoginRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AdminLogin", "adminID", id));


        adminLogin.setPassword(adminDetails.getPassword());

        final AdminLogin updatedAdmin = adminLoginRepository.save(adminLogin);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteAdmin(@PathVariable Long id) {
        AdminLogin adminLogin = adminLoginRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AdminLogin", "adminID", id));


        adminLoginRepository.delete(adminLogin);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
