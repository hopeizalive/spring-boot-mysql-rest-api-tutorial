package com.example.easynotes.repository;

import com.example.easynotes.model.AdminLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLoginRepository extends JpaRepository<AdminLogin, Long> {
}
