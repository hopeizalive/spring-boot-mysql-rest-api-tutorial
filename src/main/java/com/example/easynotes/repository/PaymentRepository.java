package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.easynotes.model.Payment;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Custom query methods if needed
}
