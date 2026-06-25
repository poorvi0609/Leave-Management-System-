package com.tekenlight.oms.modules.user.repositories;

import com.tekenlight.oms.modules.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByDepartmentId(String departmentId);

    List<User> findByDesignationId(String designationId);

    List<User> findByReportingManagerId(String managerId);

    List<User> findByStatus(User.UserStatus status);

    List<User> findByRole(User.Role role);

    boolean existsByDepartmentId(String departmentId);

    boolean existsByDesignationId(String designationId);

    boolean existsByReportingManagerId(String managerId);
}