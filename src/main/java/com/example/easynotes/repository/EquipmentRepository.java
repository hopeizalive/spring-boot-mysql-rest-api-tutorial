package com.example.easynotes.repository;
import com.example.easynotes.model.AdminLogin;
import com.example.easynotes.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}