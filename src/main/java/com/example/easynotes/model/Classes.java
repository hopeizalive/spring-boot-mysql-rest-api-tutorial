package com.example.easynotes.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long classID;

    @Column(length = 100)
    private String className;

    @Column(columnDefinition = "TEXT")
    private String classDescription;

    private String classSchedule;

    private Integer maximumCapacity;

    @OneToMany(mappedBy = "classes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeClass> employeeClass = new ArrayList<>();


    @OneToMany(mappedBy = "classes", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @JsonManagedReference
    private List<MemberClass> memberClass = new ArrayList<>();



    public long getClassID() {
        return classID;
    }

    public void setClassID(long classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public String getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(String classSchedule) {
        this.classSchedule = classSchedule;
    }

    public Integer getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(Integer maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public List<EmployeeClass> getEmployeeClass() {
        return employeeClass;
    }

    public void setEmployeeClass(List<EmployeeClass> employeeClass) {
        this.employeeClass = employeeClass;
    }

    public List<MemberClass> getMemberClass() {return memberClass;}

    public void setMemberClass(List<MemberClass> memberClass) {this.memberClass = memberClass;}

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Payment> payments = new ArrayList<>();

    public List<Payment> getPayments() {return payments;}

    public void setPayments(List<Payment> payments) {this.payments = payments;}
}

