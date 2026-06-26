package com.tekenlight.lms.module.leavetype.service;

import com.tekenlight.lms.module.leavetype.entity.LeaveType;
import com.tekenlight.lms.module.leavetype.repository.LeaveTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveTypeService {

    private final LeaveTypeRepository leaveTypeRepository;
    public LeaveType create(LeaveType leaveType) {
        if (leaveTypeRepository.existsByLeaveCode(leaveType.getLeaveCode()))
            throw new IllegalArgumentException("Leave code already exists: " + leaveType.getLeaveCode());
        if (leaveTypeRepository.existsByLeaveName(leaveType.getLeaveName()))
            throw new IllegalArgumentException("Leave name already exists: " + leaveType.getLeaveName());
        return leaveTypeRepository.save(leaveType);
    }
    public List<LeaveType> getAll() {
        return leaveTypeRepository.findAll();
    }
    public List<LeaveType> getActive() {
        return leaveTypeRepository.findByActive(true);
    }
    public LeaveType getById(Long id) {
        return leaveTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Leave type not found: " + id));
    }
    public LeaveType getByCode(String leaveCode) {
        return leaveTypeRepository.findByLeaveCode(leaveCode).orElseThrow(() -> new EntityNotFoundException("Leave type not found: " + leaveCode));
    }
    public LeaveType update(Long id, LeaveType updated) {
        LeaveType existing = getById(id);
        existing.setLeaveCode(updated.getLeaveCode());
        existing.setLeaveName(updated.getLeaveName());
        existing.setDescription(updated.getDescription());
        existing.setMaxDaysAllowed(updated.getMaxDaysAllowed());
        existing.setPaidLeave(updated.isPaidLeave());
        existing.setHalfDayAllowed(updated.isHalfDayAllowed());
        existing.setApplicableFor(updated.getApplicableFor());
        existing.setNoticePeriodDays(updated.getNoticePeriodDays());
        existing.setActive(updated.isActive());
        return leaveTypeRepository.save(existing);
    }
    public void delete(Long id) {
        LeaveType existing = getById(id);
        existing.setActive(false);
        leaveTypeRepository.save(existing);
    }
}