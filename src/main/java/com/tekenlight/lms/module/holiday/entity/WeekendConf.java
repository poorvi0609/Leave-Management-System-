package com.tekenlight.lms.module.holiday.entity;

import com.tekenlight.lms.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "weekend_configs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class WeekendConf extends BaseEntity {
    @NotBlank
    @Column(nullable = false, unique = true)
    private String location;
    @NotBlank
    @Column(nullable = false)
    private String weekendDays;
    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;
}