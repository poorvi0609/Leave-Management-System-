package com.tekenlight.oms.modules.user.dto;

import com.tekenlight.oms.modules.user.entities.User;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private User.Role role;
    private String departmentId;
    private String designationId;
    private String reportingManagerId;
    private User.UserStatus status;
    private LocalDate joiningDate;
}