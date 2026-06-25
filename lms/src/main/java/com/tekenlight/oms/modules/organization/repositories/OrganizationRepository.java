package com.tekenlight.oms.modules.organization.repositories;

import com.tekenlight.oms.modules.organization.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {

    Optional<Organization> findByEmail(String email);

    Optional<Organization> findBySubDomain(String subDomain);

    List<Organization> findByStatus(Organization.OrgStatus status);

    List<Organization> findByParentOrgId(String parentOrgId);

    boolean existsByEmail(String email);

    boolean existsBySubDomain(String subDomain);
}