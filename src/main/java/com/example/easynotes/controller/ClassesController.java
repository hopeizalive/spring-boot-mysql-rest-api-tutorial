package com.example.easynotes.controller;

import com.example.easynotes.model.Classes;
import com.example.easynotes.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {

    @Autowired
    private ClassesRepository classesRepository;

    // GET all classes
    @GetMapping
    public List<Classes> getAllClasses() {
        return classesRepository.findAll();
    }

    // GET class by ID
    @GetMapping("/{id}")
    public Optional<Classes> getClassById(@PathVariable long id) {
        return classesRepository.findById(id);
    }

    // POST create a new class
    @PostMapping
    public Classes createClass(@RequestBody Classes classes) {
        return classesRepository.save(classes);
    }

    // PUT update an existing class
    @PutMapping("/{id}")
    public Classes updateClass(@PathVariable long id, @RequestBody Classes classDetails) {
        return classesRepository.findById(id)
                .map(classes -> {
                    classes.setClassName(classDetails.getClassName());
                    classes.setClassDescription(classDetails.getClassDescription());
                    classes.setClassSchedule(classDetails.getClassSchedule());
                    classes.setMaximumCapacity(classDetails.getMaximumCapacity());
                    return classesRepository.save(classes);
                }).orElseThrow(() -> new RuntimeException("Class not found"));
    }

    // DELETE a class
    @DeleteMapping("/{id}")
    public void deleteClass(@PathVariable long id) {
        classesRepository.deleteById(id);
    }
}
