package com.tekenlight.oms.modules.organization.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class DepartmentResponseDto {

    private String id;
    private String name;
    private String code;
    private String headEmployeeId;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}