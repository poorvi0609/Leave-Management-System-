package com.tekenlight.oms.modules.organization.service;

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

    public Department create(Department department) {
        if (departmentRepository.existsByCode(department.getCode())) {
            throw new RuntimeException("Department with this code already exists");
        }
        if (departmentRepository.existsByName(department.getName())) {
            throw new RuntimeException("Department with this name already exists");
        }
        return departmentRepository.save(department);
    }

    public List<Department> getAll() {
        return departmentRepository.findByIsActive(true);
    }

    public Department getById(String id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public Department update(String id, Department updated) {
        Department existing = getById(id);
        existing.setName(updated.getName());
        existing.setCode(updated.getCode());
        return departmentRepository.save(existing);
    }

    public void delete(String id) {
        if (userRepository.existsByDepartmentId(id)) {
            throw new RuntimeException("Cannot delete department with existing employees");
        }
        Department department = getById(id);
        department.setIsActive(false);
        departmentRepository.save(department);
    }

    public Department assignHead(String departmentId, String employeeId) {
        Department department = getById(departmentId);
        department.setHeadEmployeeId(employeeId);
        return departmentRepository.save(department);
    }
}