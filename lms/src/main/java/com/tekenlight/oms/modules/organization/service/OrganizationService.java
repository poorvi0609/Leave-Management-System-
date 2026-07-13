package com.tekenlight.oms.modules.organization.service;

import com.tekenlight.oms.modules.organization.dto.OrganizationRequestDto;
import com.tekenlight.oms.modules.organization.dto.OrganizationResponseDto;
import com.tekenlight.oms.modules.organization.entities.Organization;
import com.tekenlight.oms.modules.organization.repositories.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationResponseDto create(OrganizationRequestDto dto) {
        if (organizationRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Organization with this email already exists");
        }
        Organization organization = new Organization();
        organization.setOrgName(dto.getOrgName());
        organization.setOrgDesc(dto.getOrgDesc());
        organization.setEmail(dto.getEmail());
        organization.setPhoneNo(dto.getPhoneNo());
        organization.setWebsite(dto.getWebsite());
        organization.setLogoUrl(dto.getLogoUrl());
        organization.setStatus(dto.getStatus() != null ? dto.getStatus() : Organization.OrgStatus.INCOMPLETE);
        organization.setType(dto.getType());
        organization.setSubDomain(dto.getSubDomain());
        return toResponseDto(organizationRepository.save(organization));
    }

    public List<OrganizationResponseDto> getAll() {
        return organizationRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public OrganizationResponseDto getById(String id) {
        return toResponseDto(organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found")));
    }

    public OrganizationResponseDto update(String id, OrganizationRequestDto dto) {
        Organization existing = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        existing.setOrgName(dto.getOrgName());
        existing.setOrgDesc(dto.getOrgDesc());
        existing.setEmail(dto.getEmail());
        existing.setPhoneNo(dto.getPhoneNo());
        existing.setWebsite(dto.getWebsite());
        existing.setStatus(dto.getStatus());
        existing.setType(dto.getType());
        existing.setSubDomain(dto.getSubDomain());
        return toResponseDto(organizationRepository.save(existing));
    }

    public void delete(String id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        organization.setStatus(Organization.OrgStatus.INACTIVE);
        organizationRepository.save(organization);
    }

    private OrganizationResponseDto toResponseDto(Organization organization) {
        OrganizationResponseDto dto = new OrganizationResponseDto();
        dto.setId(organization.getId());
        dto.setOrgName(organization.getOrgName());
        dto.setOrgDesc(organization.getOrgDesc());
        dto.setEmail(organization.getEmail());
        dto.setPhoneNo(organization.getPhoneNo());
        dto.setWebsite(organization.getWebsite());
        dto.setLogoUrl(organization.getLogoUrl());
        dto.setStatus(organization.getStatus());
        dto.setType(organization.getType());
        dto.setSubDomain(organization.getSubDomain());
        dto.setCreatedAt(organization.getCreatedAt());
        dto.setUpdatedAt(organization.getUpdatedAt());
        return dto;
    }
}