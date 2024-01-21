package com.springboot.organizationservice.service.impl;

import com.springboot.organizationservice.dto.OrganizationDto;
import com.springboot.organizationservice.entity.Organization;
import com.springboot.organizationservice.repository.OrganizationRepository;
import com.springboot.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        Organization organization = Organization.of(organizationDto);

        Organization savedOrganization = organizationRepository.save(organization);


        return OrganizationDto.of(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);

        return OrganizationDto.of(organization);
    }
}
