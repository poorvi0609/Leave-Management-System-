package com.tekenlight.oms.modules.organization.entities;

import com.tekenlight.oms.core.entities.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "organizations")
@Getter
@Setter
@NoArgsConstructor
public class Organization extends BaseEntity {

    @Size(max = 1000)
    @Column(length = 1000)
    private String orgDesc;

    @Size(max = 60)
    @Column(length = 60, nullable = false)
    private String orgName;

    @Size(max = 128)
    @Column(length = 128)
    private String email;

    @Column(length = 15)
    private String phoneNo;

    @Size(max = 200)
    @Column(length = 200)
    private String website;

    @Size(max = 150)
    @Column(length = 150)
    private String logoUrl;

    @Enumerated(EnumType.STRING)
    @Column(length = 16, nullable = false)
    private OrgStatus status;

    @Column(length = 45)
    private String type;

    @Column(name = "sub_domain", length = 60)
    private String subDomain;

    @Column(name = "allow_multiple_session")
    private boolean multipleSessionAllowed;

    @Column(name = "is_group_org", columnDefinition = "boolean default false")
    private boolean isGroupOrg;

    @Column(name = "parent_org_id", length = 60)
    private String parentOrgId;

    @PrePersist
    public void addDefaults() {
        super.fillModel();
        if (status == null) {
            setStatus(OrgStatus.INCOMPLETE);
        }
    }

    public enum OrgStatus {
        ACTIVE, INACTIVE, INCOMPLETE
    }
}