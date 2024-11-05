package com.example.easynotes.model;
import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


@Entity
public class EmployeeClass {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "employee_id")
        private Employee employee;

        @ManyToOne
        @JoinColumn(name = "classes_id")
        private Classes classes;


        private String additionalInfo; // You can add extra fields if needed

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {this.classes = classes;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id = id;}

    public Employee getEmployee() {return employee;}

    public void setEmployee(Employee employee) {this.employee = employee;}

    public String getAdditionalInfo() {return additionalInfo;}

    public void setAdditionalInfo(String additionalInfo) {this.additionalInfo = additionalInfo;}

    // Getters and setters
    }


