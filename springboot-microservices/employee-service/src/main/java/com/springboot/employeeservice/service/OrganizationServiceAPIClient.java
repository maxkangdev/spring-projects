package com.springboot.employeeservice.service;

import com.springboot.employeeservice.dto.DepartmentDto;
import com.springboot.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationServiceAPIClient {
    @GetMapping("api/organizations/{organization-code}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("organization-code") String organizationCode);

}
