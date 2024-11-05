package com.example.easynotes.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long classID;

    @Column(length = 100)
    private String className;

    @Column(columnDefinition = "TEXT")
    private String classDescription;

    @Column(length = 30)
    private String classSchedule;

    private Integer maximumCapacity;

    @OneToMany(mappedBy = "classes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeClass> employeeClass = new ArrayList<>();

    // Getters and Setters
    public long getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
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


}

