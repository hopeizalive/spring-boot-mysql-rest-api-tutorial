package com.example.easynotes.controller;
import com.example.easynotes.model.*;

import com.example.easynotes.repository.ClassesRepository;
import com.example.easynotes.repository.MemberRepository;
import com.example.easynotes.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.time.LocalDate;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ClassesRepository classesRepository;

    // POST: Create a new payment
    @PostMapping
    public Payment createPayment(@RequestBody Map<String, Object> paymentData) {
        Long memberId = ((Number) paymentData.get("memberId")).longValue();
        Long classId = ((Number) paymentData.get("classId")).longValue();
        Double amount = ((Number) paymentData.get("amount")).doubleValue();
        LocalDate paymentDate = LocalDate.parse((String) paymentData.get("paymentDate"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Classes classes = classesRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        Payment payment = new Payment();
        payment.setMember(member);
        payment.setClasses(classes);
        payment.setAmount(amount);
        payment.setPaymentDate(paymentDate);

        return paymentRepository.save(payment);
    }

    // GET: Retrieve all payments with pagination
    @GetMapping
    public Page<Payment> getAllPayments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return paymentRepository.findAll(pageable);
    }


    // PUT: Update an existing payment
    @PutMapping("/{paymentId}")
    public Payment updatePayment(@PathVariable Long paymentId, @RequestBody Map<String, Object> paymentData) {
        // Find the payment to update
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // Extract the fields from paymentData and set them in payment
        Long memberId = ((Number) paymentData.get("memberId")).longValue();
        Long classId = ((Number) paymentData.get("classId")).longValue();
        Double amount = ((Number) paymentData.get("amount")).doubleValue();
        LocalDate paymentDate = LocalDate.parse((String) paymentData.get("paymentDate"));

        // Set member if memberId is provided
        if (memberId != null) {
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new RuntimeException("Member not found"));
            payment.setMember(member);
        }

        // Set class if classId is provided
        if (classId != null) {
            Classes classes = classesRepository.findById(classId)
                    .orElseThrow(() -> new RuntimeException("Class not found"));
            payment.setClasses(classes);
        }

        // Set amount and date if provided
        payment.setAmount(amount);
        payment.setPaymentDate(paymentDate);

        // Save the updated payment
        return paymentRepository.save(payment);
    }

    // DELETE: Delete a payment by ID
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable Long paymentId) {
        // Check if the payment exists
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // Delete the payment
        paymentRepository.delete(payment);

        return ResponseEntity.ok("Payment deleted successfully");
    }

}
