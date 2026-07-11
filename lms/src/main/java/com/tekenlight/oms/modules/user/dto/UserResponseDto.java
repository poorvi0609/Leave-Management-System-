package com.tekenlight.oms.modules.user.dto;

import com.tekenlight.oms.modules.user.entities.User;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private User.Role role;
    private String departmentId;
    private String departmentName;
    private String designationId;
    private String designationName;
    private String reportingManagerId;
    private String reportingManagerName;
    private User.UserStatus status;
    private LocalDate joiningDate;
    private LocalDateTime createdAt;
}