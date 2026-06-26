package com.tekenlight.lms.module.leavepolicy.service;

import com.tekenlight.lms.module.leavepolicy.entity.LeavePolicy;
import com.tekenlight.lms.module.leavepolicy.repository.LeavePolicyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeavePolicyService {
    private final LeavePolicyRepository leavePolicyRepository;
    public LeavePolicy create(LeavePolicy leavePolicy) {
        if (leavePolicyRepository.existsByPolicyCode(leavePolicy.getPolicyCode()))
            throw new IllegalArgumentException("Policy code already exists: " + leavePolicy.getPolicyCode());
        if (leavePolicyRepository.existsByPolicyName(leavePolicy.getPolicyName()))
            throw new IllegalArgumentException("Policy name already exists: " + leavePolicy.getPolicyName());
        return leavePolicyRepository.save(leavePolicy);
    }
    public List<LeavePolicy> getAll() {
        return leavePolicyRepository.findAll();
    }
    public List<LeavePolicy> getActive() {
        return leavePolicyRepository.findByActive(true);
    }
    public LeavePolicy getById(Long id) {
        return leavePolicyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leave policy not found: " + id));
    }
    public LeavePolicy getByCode(String policyCode) {
        return leavePolicyRepository.findByPolicyCode(policyCode)
                .orElseThrow(() -> new EntityNotFoundException("Leave policy not found: " + policyCode));
    }
    public LeavePolicy update(Long id, LeavePolicy updated) {
        LeavePolicy existing = getById(id);
        existing.setPolicyCode(updated.getPolicyCode());
        existing.setPolicyName(updated.getPolicyName());
        existing.setDescription(updated.getDescription());
        existing.setMaxLeavesPerYear(updated.getMaxLeavesPerYear());
        existing.setNoticePeriodDays(updated.getNoticePeriodDays());
        existing.setMaxConsecutiveDays(updated.getMaxConsecutiveDays());
        existing.setMinLeaveDays(updated.getMinLeaveDays());
        existing.setPaidLeave(updated.isPaidLeave());
        existing.setHalfDayAllowed(updated.isHalfDayAllowed());
        existing.setWeekendIncluded(updated.isWeekendIncluded());
        existing.setHolidayIncluded(updated.isHolidayIncluded());
        existing.setApplicableFor(updated.getApplicableFor());
        existing.setApprovalRequired(updated.isApprovalRequired());
        existing.setApprovalRole(updated.getApprovalRole());
        existing.setApprovalLevel(updated.getApprovalLevel());
        existing.setMultiLevelApproval(updated.isMultiLevelApproval());
        existing.setActive(updated.isActive());
        return leavePolicyRepository.save(existing);
    }
    public void delete(Long id) {
        LeavePolicy existing = getById(id);
        existing.setActive(false);
        leavePolicyRepository.save(existing);
    }
}