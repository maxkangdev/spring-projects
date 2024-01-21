package com.springboot.employeeservice.service;

import com.springboot.employeeservice.dto.APIResponseDto;
import com.springboot.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long id);
}