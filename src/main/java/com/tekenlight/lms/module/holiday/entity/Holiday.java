package com.tekenlight.lms.module.holiday.entity;

import com.tekenlight.lms.core.entity.BaseEntity;
import com.tekenlight.lms.module.holiday.enums.HolidayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "holidays", uniqueConstraints = @UniqueConstraint(name = "unq_date_location", columnNames = {"hol_date", "location"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Holiday extends BaseEntity {
    @NotBlank
    @Column(nullable = false, unique = true)
    private String holCode;
    @NotBlank
    @Column(nullable = false)
    private String holName;
    @NotNull
    @Column(nullable = false)
    private LocalDate holDate;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HolidayType holType;
    private String location;
    private String desc;
    @Column(nullable = false)
    private boolean optionalHol;
    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;
}