package com.example.easynotes.controller;

import com.example.easynotes.model.Employee;
import com.example.easynotes.repository.ClassesRepository;
import com.example.easynotes.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.easynotes.repository.EmployeeClassRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private EmployeeClassRepository employeeClassRepository;

    // GET all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Integer id) {
        return employeeRepository.findById(id);
    }

    // POST create a new employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // PUT update an existing employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employeeDetails) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(employeeDetails.getName());
                    employee.setDateOfBirth(employeeDetails.getDateOfBirth());
                    employee.setContactNumber(employeeDetails.getContactNumber());
                    employee.setEmailAddress(employeeDetails.getEmailAddress());
                    employee.setAddress(employeeDetails.getAddress());
                    employee.setHireDate(employeeDetails.getHireDate());
                    employee.setEmployeeStatus(employeeDetails.getEmployeeStatus());
                    employee.setPassword(employeeDetails.getPassword());
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // DELETE an employee
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeRepository.deleteById(id);
    }
}
