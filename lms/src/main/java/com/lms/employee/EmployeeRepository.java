package com.lms.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByDesignationId(Long designationId);

    List<Employee> findByReportingManagerId(Long managerId);

    List<Employee> findByStatus(Employee.EmployeeStatus status);

    List<Employee> findByRole(Employee.Role role);

    boolean existsByDepartmentId(Long departmentId);

    boolean existsByDesignationId(Long designationId);

    boolean existsByReportingManagerId(Long managerId);
}