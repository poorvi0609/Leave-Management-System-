package com.tekenlight.oms.modules.organization.controllers;

import com.tekenlight.oms.modules.organization.dto.DepartmentRequestDto;
import com.tekenlight.oms.modules.organization.dto.DepartmentResponseDto;
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
    public ResponseEntity<DepartmentResponseDto> create(@RequestBody DepartmentRequestDto dto) {
        return ResponseEntity.ok(departmentService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> getAll() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(departmentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> update(@PathVariable String id, @RequestBody DepartmentRequestDto dto) {
        return ResponseEntity.ok(departmentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/head")
    public ResponseEntity<DepartmentResponseDto> assignHead(@PathVariable String id, @RequestParam String employeeId) {
        return ResponseEntity.ok(departmentService.assignHead(id, employeeId));
    }
}