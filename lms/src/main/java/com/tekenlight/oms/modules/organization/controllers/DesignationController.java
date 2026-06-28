package com.tekenlight.oms.modules.organization.controllers;

import com.tekenlight.oms.modules.organization.entities.Designation;
import com.tekenlight.oms.modules.organization.service.DesignationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/designations")
@RequiredArgsConstructor
public class DesignationController {

    private final DesignationService designationService;

    @PostMapping
    public ResponseEntity<Designation> create(@RequestBody Designation designation) {
        return ResponseEntity.ok(designationService.create(designation));
    }

    @GetMapping
    public ResponseEntity<List<Designation>> getAll() {
        return ResponseEntity.ok(designationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Designation> getById(@PathVariable String id) {
        return ResponseEntity.ok(designationService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Designation> update(@PathVariable String id, @RequestBody Designation designation) {
        return ResponseEntity.ok(designationService.update(id, designation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        designationService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 