package com.tekenlight.oms.modules.organization.entities;

import com.tekenlight.oms.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
public class Department extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(name = "head_employee_id")
    private String headEmployeeId;

    @Column(nullable = false)
    private Boolean isActive = true;
}