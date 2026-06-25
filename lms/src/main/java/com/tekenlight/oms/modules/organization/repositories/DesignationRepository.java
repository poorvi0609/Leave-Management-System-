package com.tekenlight.oms.modules.organization.repositories;

import com.tekenlight.oms.modules.organization.entities.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, String> {

    Optional<Designation> findByCode(String code);

    Optional<Designation> findByName(String name);

    List<Designation> findByIsActive(Boolean isActive);

    List<Designation> findByGradeLevelOrderByGradeLevelAsc(Integer gradeLevel);

    boolean existsByGradeLevel(Integer gradeLevel);
}