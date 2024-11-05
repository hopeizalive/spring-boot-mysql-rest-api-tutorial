package com.example.easynotes.repository;
import com.example.easynotes.model.EmployeeClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface EmployeeClassRepository extends JpaRepository<EmployeeClass, Long> {}
