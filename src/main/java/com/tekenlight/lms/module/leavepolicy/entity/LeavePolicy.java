package com.tekenlight.lms.module.leavepolicy.entity;

import com.tekenlight.lms.core.entity.BaseEntity;
import com.tekenlight.lms.module.leavepolicy.enums.ApprovalRole;
import com.tekenlight.lms.module.leavetype.enums.ApplicableFor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "leave_policies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class LeavePolicy extends BaseEntity {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String policyCode;
    @NotBlank
    @Column(nullable = false)
    private String policyName;
    private String description;
    private Integer maxLeavesPerYear;
    private Integer noticePeriodDays;
    private Integer maxConsecutiveDays;
    private Integer minLeaveDays;

    @Column(nullable = false)
    private boolean paidLeave;
    @Column(nullable = false)
    private boolean halfDayAllowed;
    @Column(nullable = false)
    private boolean weekendIncluded;
    @Column(nullable = false)
    private boolean holidayIncluded;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private ApplicableFor applicableFor = ApplicableFor.ALL_EMPLOYEES;
    @Column(nullable = false)
    private boolean approvalRequired;
    @Enumerated(EnumType.STRING)
    private ApprovalRole approvalRole;
    private Integer approvalLevel;
    @Column(nullable = false)
    private boolean multiLevelApproval;
    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;
}