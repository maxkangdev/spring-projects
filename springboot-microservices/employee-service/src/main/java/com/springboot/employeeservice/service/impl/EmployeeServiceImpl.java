package com.springboot.employeeservice.service.impl;

import com.springboot.employeeservice.dto.EmployeeDto;
import com.springboot.employeeservice.entity.Employee;
import com.springboot.employeeservice.repository.EmployeeRepository;
import com.springboot.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = Employee.of(employeeDto);
        Employee saveDEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = EmployeeDto.of(saveDEmployee);

        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        return EmployeeDto.of(employee);
    }
}
