package com.tekenlight.oms.modules.organization.service;

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

    public Designation create(Designation designation) {
        if (designationRepository.existsByCode(designation.getCode())) {
            throw new RuntimeException("Designation with this code already exists");
        }
        if (designationRepository.existsByName(designation.getName())) {
            throw new RuntimeException("Designation with this name already exists");
        }
        return designationRepository.save(designation);
    }

    public List<Designation> getAll() {
        return designationRepository.findByIsActive(true);
    }

    public Designation getById(String id) {
        return designationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designation not found"));
    }

    public Designation update(String id, Designation updated) {
        Designation existing = getById(id);
        existing.setName(updated.getName());
        existing.setCode(updated.getCode());
        existing.setGradeLevel(updated.getGradeLevel());
        return designationRepository.save(existing);
    }

    public void delete(String id) {
        if (userRepository.existsByDesignationId(id)) {
            throw new RuntimeException("Cannot delete designation with existing employees");
        }
        Designation designation = getById(id);
        designation.setIsActive(false);
        designationRepository.save(designation);
    }
}