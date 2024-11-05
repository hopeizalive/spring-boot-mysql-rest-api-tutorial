package com.example.easynotes.controller;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Equipment;
import com.example.easynotes.model.Note;
import com.example.easynotes.model.Person;
import com.example.easynotes.repository.EquipmentRepository;
import com.example.easynotes.repository.NoteRepository;
import com.example.easynotes.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @GetMapping
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();

    }

    // GET person by ID

    @GetMapping("/{id}")
    public Optional<Equipment> getEquipmentById(@PathVariable Long id) {
        return equipmentRepository.findById(id);
    }

    @PostMapping
    public Equipment createEquipment(@RequestBody Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @DeleteMapping("/{id}")
    public String deleteEquipment(@PathVariable Long id) {
        equipmentRepository.deleteById(id);
        return "Equipment with ID: " + id + " has been deleted from the list of equipments ";
    }


    // PUT endpoint to update equipment
    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(
            @PathVariable(value = "id") Long equipmentId,
            @Valid @RequestBody Equipment equipmentDetails) {

        // Find the equipment by ID
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not found for this id :: ", "equipment id:", + equipmentId));

        // Update the equipment details
        equipment.setEquipmentName(equipmentDetails.getEquipmentName());
        equipment.setEquipmentDescription(equipmentDetails.getEquipmentDescription());
        equipment.setEquipmentType(equipmentDetails.getEquipmentType());
        equipment.setPurchaseDate(equipmentDetails.getPurchaseDate());
        equipment.setPurchasePrice(equipmentDetails.getPurchasePrice());
        equipment.setCurrentStatus(equipmentDetails.getCurrentStatus());

        // Save the updated equipment
        final Equipment updatedEquipment = equipmentRepository.save(equipment);
        return ResponseEntity.ok(updatedEquipment);
    }

}
