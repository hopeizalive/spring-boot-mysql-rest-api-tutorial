package com.example.easynotes.controller;
import com.example.easynotes.model.Person;
import com.example.easynotes.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.easynotes.model.Address;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public List<Person> getAllPersons() {
        return personRepository.findAll();

    }

    ;
    // GET person by ID

    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable Long id) {
        return personRepository.findById(id);
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        return personRepository.findById(id).map(person -> {
            person.setName(personDetails.getName());
            person.setAge(personDetails.getAge());

            Address address = person.getAddress();
            if (address != null && personDetails.getAddress() != null) {
                address.setStreet(personDetails.getAddress().getStreet());
                address.setCity(personDetails.getAddress().getCity());
                address.setState(personDetails.getAddress().getState());
                address.setZipcode(personDetails.getAddress().getZipcode());
                person.setAddress(address); // Update address relationship
            }

            return personRepository.save(person);

        }).orElseThrow(() -> new RuntimeException("Person not found with id " + id));



    }

    @DeleteMapping("/{ID}")
    public String deletePerson(@PathVariable Long ID) {
        personRepository.deleteById(ID);
        return "Person with ID: " + ID + " has been deleted from the list of persons ";
    }
    }
