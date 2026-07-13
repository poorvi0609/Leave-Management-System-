package com.tekenlight.oms.modules.organization.dto;

import com.tekenlight.oms.modules.organization.entities.Organization;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrganizationResponseDto {

    private String id;
    private String orgName;
    private String orgDesc;
    private String email;
    private String phoneNo;
    private String website;
    private String logoUrl;
    private Organization.OrgStatus status;
    private String type;
    private String subDomain;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}