package com.tekenlight.oms.modules.organization.service;

import com.tekenlight.oms.modules.organization.dto.DepartmentRequestDto;
import com.tekenlight.oms.modules.organization.dto.DepartmentResponseDto;
import com.tekenlight.oms.modules.organization.entities.Department;
import com.tekenlight.oms.modules.organization.repositories.DepartmentRepository;
import com.tekenlight.oms.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public DepartmentResponseDto create(DepartmentRequestDto dto) {
        if (departmentRepository.existsByCode(dto.getCode())) {
            throw new RuntimeException("Department with this code already exists");
        }
        if (departmentRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Department with this name already exists");
        }
        Department department = new Department();
        department.setName(dto.getName());
        department.setCode(dto.getCode());
        department.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        return toResponseDto(departmentRepository.save(department));
    }

    public List<DepartmentResponseDto> getAll() {
        return departmentRepository.findByIsActive(true)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public DepartmentResponseDto getById(String id) {
        return toResponseDto(departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found")));
    }

    public DepartmentResponseDto update(String id, DepartmentRequestDto dto) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        existing.setName(dto.getName());
        existing.setCode(dto.getCode());
        return toResponseDto(departmentRepository.save(existing));
    }

    public void delete(String id) {
        if (userRepository.existsByDepartmentId(id)) {
            throw new RuntimeException("Cannot delete department with existing employees");
        }
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        department.setIsActive(false);
        departmentRepository.save(department);
    }

    public DepartmentResponseDto assignHead(String departmentId, String employeeId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        department.setHeadEmployeeId(employeeId);
        return toResponseDto(departmentRepository.save(department));
    }

    private DepartmentResponseDto toResponseDto(Department department) {
        DepartmentResponseDto dto = new DepartmentResponseDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setCode(department.getCode());
        dto.setHeadEmployeeId(department.getHeadEmployeeId());
        dto.setIsActive(department.getIsActive());
        dto.setCreatedAt(department.getCreatedAt());
        dto.setUpdatedAt(department.getUpdatedAt());
        return dto;
    }
}