package com.tekenlight.oms.modules.user.service;

import com.tekenlight.oms.modules.organization.entities.Department;
import com.tekenlight.oms.modules.organization.entities.Designation;
import com.tekenlight.oms.modules.organization.repositories.DepartmentRepository;
import com.tekenlight.oms.modules.organization.repositories.DesignationRepository;
import com.tekenlight.oms.modules.user.dto.UserRequestDto;
import com.tekenlight.oms.modules.user.dto.UserResponseDto;
import com.tekenlight.oms.modules.user.entities.User;
import com.tekenlight.oms.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DepartmentRepository departmentRepository;
    private final DesignationRepository designationRepository;

    public UserResponseDto create(UserRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Designation designation = designationRepository.findById(dto.getDesignationId())
                .orElseThrow(() -> new RuntimeException("Designation not found"));

        User reportingManager = null;
        if (dto.getReportingManagerId() != null) {
            reportingManager = userRepository.findById(dto.getReportingManagerId())
                    .orElseThrow(() -> new RuntimeException("Reporting manager not found"));
        }

        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setDepartment(department);
        user.setDesignation(designation);
        user.setReportingManager(reportingManager);
        user.setStatus(dto.getStatus() != null ? dto.getStatus() : User.UserStatus.ACTIVE);
        user.setJoiningDate(dto.getJoiningDate());

        User saved = userRepository.save(user);
        return toResponseDto(saved);
    }

    public List<UserResponseDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public UserResponseDto getById(String id) {
        return toResponseDto(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponseDto getByEmail(String email) {
        return toResponseDto(userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponseDto update(String id, UserRequestDto dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            existing.setDepartment(department);
        }

        if (dto.getDesignationId() != null) {
            Designation designation = designationRepository.findById(dto.getDesignationId())
                    .orElseThrow(() -> new RuntimeException("Designation not found"));
            existing.setDesignation(designation);
        }

        if (dto.getReportingManagerId() != null) {
            User manager = userRepository.findById(dto.getReportingManagerId())
                    .orElseThrow(() -> new RuntimeException("Reporting manager not found"));
            existing.setReportingManager(manager);
        }

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setJoiningDate(dto.getJoiningDate());

        return toResponseDto(userRepository.save(existing));
    }

    public UserResponseDto updateStatus(String id, User.UserStatus status) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(status);
        return toResponseDto(userRepository.save(user));
    }

    public UserResponseDto updateRole(String id, User.Role role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(role);
        return toResponseDto(userRepository.save(user));
    }

    public List<UserResponseDto> getReportees(String managerId) {
        return userRepository.findByReportingManagerId(managerId)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    private UserResponseDto toResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        dto.setJoiningDate(user.getJoiningDate());
        dto.setCreatedAt(user.getCreatedAt());

        if (user.getDepartment() != null) {
            dto.setDepartmentId(user.getDepartment().getId());
            dto.setDepartmentName(user.getDepartment().getName());
        }

        if (user.getDesignation() != null) {
            dto.setDesignationId(user.getDesignation().getId());
            dto.setDesignationName(user.getDesignation().getName());
        }

        if (user.getReportingManager() != null) {
            dto.setReportingManagerId(user.getReportingManager().getId());
            dto.setReportingManagerName(
                    user.getReportingManager().getFirstName() + " " +
                            user.getReportingManager().getLastName()
            );
        }

        return dto;
    }

    public User getRawUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}