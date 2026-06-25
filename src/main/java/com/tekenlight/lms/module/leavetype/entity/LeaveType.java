package com.tekenlight.lms.module.leavetype.entity;

import com.tekenlight.lms.core.entity.BaseEntity;
import com.tekenlight.lms.module.leavetype.enums.ApplicableFor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "leave_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class LeaveType extends BaseEntity {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String leaveCode;

    @NotBlank
    @Column(nullable = false)
    private String leaveName;

    private String description;

    private Integer maxDaysAllowed;

    @Column(nullable = false)
    private boolean paidLeave;
    @Column(nullable = false)
    private boolean halfDayAllowed;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private ApplicableFor applicableFor = ApplicableFor.ALL_EMPLOYEES;
    private Integer noticePeriodDays;
    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;
}