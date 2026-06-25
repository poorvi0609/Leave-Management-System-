package com.tekenlight.lms.module.leavetype.repository;

import com.tekenlight.lms.module.leavetype.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {
    Optional<LeaveType> findByLeaveCode(String leaveCode);
    boolean existsByLeaveCode(String leaveCode);
    boolean existsByLeaveName(String leaveName);
    List<LeaveType> findByActive(boolean active);
    List<LeaveType> findByPaidLeave(boolean paidLeave);
}