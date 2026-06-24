package com.lms.designation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long> {

    Optional<Designation> findByCode(String code);

    Optional<Designation> findByName(String name);

    List<Designation> findByIsActive(Boolean isActive);

    List<Designation> findByGradeLevelOrderByGradeLevelAsc(Integer gradeLevel);

    boolean existsByGradeLevel(Integer gradeLevel);
}