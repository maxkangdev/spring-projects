package com.springboot.organizationservice.service;


import com.springboot.organizationservice.dto.OrganizationDto;
import com.springboot.organizationservice.entity.Organization;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);

    OrganizationDto getOrganizationByCode(String organizationCode);
}
