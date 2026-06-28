package com.tekenlight.oms.modules.organization.service;

import com.tekenlight.oms.modules.organization.entities.Organization;
import com.tekenlight.oms.modules.organization.repositories.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Organization create(Organization organization) {
        if (organizationRepository.existsByEmail(organization.getEmail())) {
            throw new RuntimeException("Organization with this email already exists");
        }
        return organizationRepository.save(organization);
    }

    public List<Organization> getAll() {
        return organizationRepository.findAll();
    }

    public Organization getById(String id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
    }

    public Organization update(String id, Organization updated) {
        Organization existing = getById(id);
        existing.setOrgName(updated.getOrgName());
        existing.setOrgDesc(updated.getOrgDesc());
        existing.setEmail(updated.getEmail());
        existing.setPhoneNo(updated.getPhoneNo());
        existing.setWebsite(updated.getWebsite());
        existing.setStatus(updated.getStatus());
        return organizationRepository.save(existing);
    }

    public void delete(String id) {
        Organization organization = getById(id);
        organization.setStatus(Organization.OrgStatus.INACTIVE);

    }

}