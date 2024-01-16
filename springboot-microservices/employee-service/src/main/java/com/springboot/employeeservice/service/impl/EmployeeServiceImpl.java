package com.springboot.employeeservice.service.impl;

import com.springboot.employeeservice.dto.APIResponseDto;
import com.springboot.employeeservice.dto.DepartmentDto;
import com.springboot.employeeservice.dto.EmployeeDto;
import com.springboot.employeeservice.entity.Employee;
import com.springboot.employeeservice.repository.EmployeeRepository;
import com.springboot.employeeservice.service.APIClient;
import com.springboot.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.*;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    //    private RestTemplate restTemplate;
    private WebClient webClient;
//    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = Employee.of(employeeDto);
        Employee saveDEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = EmployeeDto.of(saveDEmployee);

        return savedEmployeeDto;
    }

    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();

        DepartmentDto departmentDto = webClient.get().uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

//        ResponseEntity<DepartmentDto> responseEntity = apiClient.getDepartment(employee.getDepartmentCode());
//        DepartmentDto departmentDto = responseEntity.getBody();

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(EmployeeDto.of(employee));
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception e) {
        Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development");

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(EmployeeDto.of(employee));
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }
}