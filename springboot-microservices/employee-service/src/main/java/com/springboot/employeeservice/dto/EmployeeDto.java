package com.springboot.employeeservice.dto;

import com.springboot.employeeservice.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private static ModelMapper mapper = new ModelMapper();
    public static EmployeeDto of(Employee employee){
        return mapper.map(employee,EmployeeDto.class);
    }
}