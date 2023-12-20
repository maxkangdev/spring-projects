package com.springboot.departmentservice.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.departmentservice.entity.Department;
import lombok.*;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;

    private static ModelMapper mapper = new ModelMapper();
    public static DepartmentDto of(Department department){
        return mapper.map(department,DepartmentDto.class);
    }
}