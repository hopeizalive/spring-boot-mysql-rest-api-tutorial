package com.example.easynotes.repository;
//package com.example.projectname.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.easynotes.model.Person;

import com.example.easynotes.model.Address;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    // You can define custom queries here if needed
}
