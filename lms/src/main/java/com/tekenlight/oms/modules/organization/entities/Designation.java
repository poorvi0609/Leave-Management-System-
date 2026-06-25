package com.tekenlight.oms.modules.organization.entities;

import com.tekenlight.oms.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "designations")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Designation extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private Integer gradeLevel;

    @Column(nullable = false)
    private Boolean isActive = true;
}