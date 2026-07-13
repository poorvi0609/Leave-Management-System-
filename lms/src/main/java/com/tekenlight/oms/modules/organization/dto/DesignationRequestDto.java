package com.tekenlight.oms.modules.organization.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignationRequestDto {

    private String name;
    private String code;
    private Integer gradeLevel;
    private Boolean isActive;
}