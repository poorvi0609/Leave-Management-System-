package com.tekenlight.oms.modules.organization.controllers;

import com.tekenlight.oms.modules.organization.dto.OrganizationRequestDto;
import com.tekenlight.oms.modules.organization.dto.OrganizationResponseDto;
import com.tekenlight.oms.modules.organization.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationResponseDto> create(@RequestBody OrganizationRequestDto dto) {
        return ResponseEntity.ok(organizationService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<OrganizationResponseDto>> getAll() {
        return ResponseEntity.ok(organizationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponseDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(organizationService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationResponseDto> update(@PathVariable String id, @RequestBody OrganizationRequestDto dto) {
        return ResponseEntity.ok(organizationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        organizationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}