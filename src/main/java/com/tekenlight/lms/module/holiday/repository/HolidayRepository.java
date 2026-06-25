package com.tekenlight.lms.module.holiday.repository;

import com.tekenlight.lms.module.holiday.entity.Holiday;
import com.tekenlight.lms.module.holiday.enums.HolidayType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    Optional<Holiday> findByHolCode(String holCode);
    boolean existsByHolCode(String holCode);
    List<Holiday> findByLocation(String location);
    List<Holiday> findByHolType(HolidayType holType);
    List<Holiday> findByActive(boolean active);
    List<Holiday> findByHolDateBetween(LocalDate start, LocalDate end);
    List<Holiday> findByHolDateBetweenAndLocation(LocalDate start, LocalDate end, String location);
    Optional<Holiday> findByHolDateAndLocation(LocalDate holDate, String location);
}