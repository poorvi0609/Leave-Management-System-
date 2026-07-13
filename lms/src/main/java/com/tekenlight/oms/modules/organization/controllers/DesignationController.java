package com.tekenlight.oms.modules.organization.controllers;

import com.tekenlight.oms.modules.organization.dto.DesignationRequestDto;
import com.tekenlight.oms.modules.organization.dto.DesignationResponseDto;
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
    public ResponseEntity<DesignationResponseDto> create(@RequestBody DesignationRequestDto dto) {
        return ResponseEntity.ok(designationService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<DesignationResponseDto>> getAll() {
        return ResponseEntity.ok(designationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DesignationResponseDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(designationService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DesignationResponseDto> update(@PathVariable String id, @RequestBody DesignationRequestDto dto) {
        return ResponseEntity.ok(designationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        designationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}