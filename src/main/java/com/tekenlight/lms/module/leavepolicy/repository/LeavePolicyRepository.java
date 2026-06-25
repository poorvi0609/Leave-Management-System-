package com.tekenlight.lms.module.leavepolicy.repository;

import com.tekenlight.lms.module.leavepolicy.entity.LeavePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeavePolicyRepository extends JpaRepository<LeavePolicy, Long> {
    Optional<LeavePolicy> findByPolicyCode(String policyCode);
    boolean existsByPolicyCode(String policyCode);
    boolean existsByPolicyName(String policyName);
    List<LeavePolicy> findByActive(boolean active);
    List<LeavePolicy> findByApprovalRequired(boolean approvalRequired);
    List<LeavePolicy> findByPaidLeave(boolean paidLeave);
}