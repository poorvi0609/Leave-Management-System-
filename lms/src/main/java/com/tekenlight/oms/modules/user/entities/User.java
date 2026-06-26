package com.tekenlight.oms.modules.user.entities;

import com.tekenlight.oms.core.entities.BaseEntity;
import lombok.experimental.SuperBuilder;
import com.tekenlight.oms.modules.organization.entities.Department;
import com.tekenlight.oms.modules.organization.entities.Designation;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class User extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "designation_id")
    private Designation designation;

    @ManyToOne
    @JoinColumn(name = "reporting_manager_id")
    private User reportingManager;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Column(nullable = false)
    private LocalDate joiningDate;

    public enum Role {
        EMPLOYEE, MANAGER, HR_ADMIN, SUPER_ADMIN
    }

    public enum UserStatus {
        ACTIVE, INACTIVE, ON_PROBATION
    }
}