package com.springboot.organizationservice.repository;

import com.springboot.organizationservice.dto.OrganizationDto;
import com.springboot.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findByOrganizationCode(String organizationCode);
}
