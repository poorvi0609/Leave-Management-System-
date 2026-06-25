package com.tekenlight.lms.module.holiday.service;

import com.tekenlight.lms.module.holiday.entity.Holiday;
import com.tekenlight.lms.module.holiday.enums.HolidayType;
import com.tekenlight.lms.module.holiday.repository.HolidayRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayService {

    private final HolidayRepository holidayRepository;
    public Holiday create(Holiday holiday) {
        if (holidayRepository.existsByHolCode(holiday.getHolCode()))
            throw new IllegalArgumentException("Holiday code already exists: " + holiday.getHolCode());

        holiday.setOptionalHol(holiday.getHolType() == HolidayType.OPTIONAL);
        return holidayRepository.save(holiday);
    }
    public List<Holiday> getAll() {
        return holidayRepository.findAll();
    }
    public Holiday getById(Long id) {
        return holidayRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Holiday not found: " + id));
    }
    public Holiday getByCode(String holCode) {
        return holidayRepository.findByHolCode(holCode).orElseThrow(() -> new EntityNotFoundException("Holiday not found: " + holCode));
    }
    public List<Holiday> getByDateRange(LocalDate start, LocalDate end) {
        return holidayRepository.findByHolDateBetween(start, end);
    }
    public List<Holiday> getByDateRangeAndLocation(LocalDate start, LocalDate end, String location) {
        return holidayRepository.findByHolDateBetweenAndLocation(start, end, location);
    }
    public List<Holiday> getByType(HolidayType type) {
        return holidayRepository.findByHolType(type);
    }
    public List<Holiday> getActive() {
        return holidayRepository.findByActive(true);
    }
    public Holiday update(Long id, Holiday updated) {
        Holiday existing = getById(id);
        existing.setHolCode(updated.getHolCode());
        existing.setHolName(updated.getHolName());
        existing.setHolDate(updated.getHolDate());
        existing.setHolType(updated.getHolType());
        existing.setLocation(updated.getLocation());
        existing.setDesc(updated.getDesc());
        existing.setOptionalHol(updated.getHolType() == HolidayType.OPTIONAL);
        existing.setActive(updated.isActive());
        return holidayRepository.save(existing);
    }
    public void delete(Long id) {
        Holiday existing = getById(id);
        existing.setActive(false);
        holidayRepository.save(existing);
    }
    public List<Holiday> bulkCreate(List<Holiday> holidays) {
        holidays.forEach(h -> {
            if (!holidayRepository.existsByHolCode(h.getHolCode())) {
                h.setOptionalHol(h.getHolType() == HolidayType.OPTIONAL);
                holidayRepository.save(h);
            }
        });
        return holidayRepository.findAll();
    }
}