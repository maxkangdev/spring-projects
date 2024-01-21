package com.springboot.organizationservice.dto;

import com.springboot.organizationservice.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {
    private Long id;
    private String organizationName;
    private String organizationDescription;
    private String organizationCode;
    private LocalDateTime createdDate;

    private static ModelMapper mapper = new ModelMapper();
    public static OrganizationDto of(Organization organization){
        return mapper.map(organization,OrganizationDto.class);
    }
}
