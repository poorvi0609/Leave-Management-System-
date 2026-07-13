package com.tekenlight.oms.modules.organization.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class DesignationResponseDto {

    private String id;
    private String name;
    private String code;
    private Integer gradeLevel;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}