package com.tekenlight.oms.modules.organization.service;

import com.tekenlight.oms.modules.organization.dto.DesignationRequestDto;
import com.tekenlight.oms.modules.organization.dto.DesignationResponseDto;
import com.tekenlight.oms.modules.organization.entities.Designation;
import com.tekenlight.oms.modules.organization.repositories.DesignationRepository;
import com.tekenlight.oms.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignationService {

    private final DesignationRepository designationRepository;
    private final UserRepository userRepository;

    public DesignationResponseDto create(DesignationRequestDto dto) {
        if (designationRepository.existsByCode(dto.getCode())) {
            throw new RuntimeException("Designation with this code already exists");
        }
        if (designationRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Designation with this name already exists");
        }
        Designation designation = new Designation();
        designation.setName(dto.getName());
        designation.setCode(dto.getCode());
        designation.setGradeLevel(dto.getGradeLevel());
        designation.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        return toResponseDto(designationRepository.save(designation));
    }

    public List<DesignationResponseDto> getAll() {
        return designationRepository.findByIsActive(true)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public DesignationResponseDto getById(String id) {
        return toResponseDto(designationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designation not found")));
    }

    public DesignationResponseDto update(String id, DesignationRequestDto dto) {
        Designation existing = designationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designation not found"));
        existing.setName(dto.getName());
        existing.setCode(dto.getCode());
        existing.setGradeLevel(dto.getGradeLevel());
        return toResponseDto(designationRepository.save(existing));
    }

    public void delete(String id) {
        if (userRepository.existsByDesignationId(id)) {
            throw new RuntimeException("Cannot delete designation with existing employees");
        }
        Designation designation = designationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designation not found"));
        designation.setIsActive(false);
        designationRepository.save(designation);
    }

    private DesignationResponseDto toResponseDto(Designation designation) {
        DesignationResponseDto dto = new DesignationResponseDto();
        dto.setId(designation.getId());
        dto.setName(designation.getName());
        dto.setCode(designation.getCode());
        dto.setGradeLevel(designation.getGradeLevel());
        dto.setIsActive(designation.getIsActive());
        dto.setCreatedAt(designation.getCreatedAt());
        dto.setUpdatedAt(designation.getUpdatedAt());
        return dto;
    }
}