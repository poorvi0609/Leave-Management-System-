package com.tekenlight.oms.modules.organization.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequestDto {

    private String name;
    private String code;
    private String headEmployeeId;
    private Boolean isActive;
}