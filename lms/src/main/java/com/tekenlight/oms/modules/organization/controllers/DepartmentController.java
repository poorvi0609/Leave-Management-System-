package com.tekenlight.oms.modules.organization.controllers;

import com.tekenlight.oms.modules.organization.entities.Department;
import com.tekenlight.oms.modules.organization.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.create(department));
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable String id) {
        return ResponseEntity.ok(departmentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable String id, @RequestBody Department department) {
        return ResponseEntity.ok(departmentService.update(id, department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/head")
    public ResponseEntity<Department> assignHead(@PathVariable String id, @RequestParam String employeeId) {
        return ResponseEntity.ok(departmentService.assignHead(id, employeeId));
    }
}