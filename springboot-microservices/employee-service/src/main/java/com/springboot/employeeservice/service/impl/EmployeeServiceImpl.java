package com.springboot.employeeservice.service.impl;

import com.springboot.employeeservice.dto.APIResponseDto;
import com.springboot.employeeservice.dto.DepartmentDto;
import com.springboot.employeeservice.dto.EmployeeDto;
import com.springboot.employeeservice.dto.OrganizationDto;
import com.springboot.employeeservice.entity.Employee;
import com.springboot.employeeservice.repository.EmployeeRepository;
import com.springboot.employeeservice.service.DepartmentServiceAPIClient;
import com.springboot.employeeservice.service.EmployeeService;
import com.springboot.employeeservice.service.OrganizationServiceAPIClient;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    //    private RestTemplate restTemplate;
    private WebClient webClient;
    private DepartmentServiceAPIClient departmentServiceApiClient;
    private OrganizationServiceAPIClient organizationServiceAPIClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = Employee.of(employeeDto);
        Employee saveDEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = EmployeeDto.of(saveDEmployee);

        return savedEmployeeDto;
    }

    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        LOGGER.info("call getEmployeeById");
        Employee employee = employeeRepository.findById(employeeId).get();

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();

//        DepartmentDto departmentDto = webClient.get().uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        ResponseEntity<DepartmentDto> departmentDtoResponseEntity = departmentServiceApiClient.getDepartment(employee.getDepartmentCode());
        DepartmentDto departmentDto = departmentDtoResponseEntity.getBody();

//        OrganizationDto organizationDto = webClient.get()
//                .uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
//                .retrieve()
//                .bodyToMono(OrganizationDto.class)
//                .block();
//        ResponseEntity<OrganizationDto> responseEntity = restTemplate.getForEntity("http://localhost:8083/api/organizations/" + employee.getOrganizationCode(), OrganizationDto.class);
//        OrganizationDto organizationDto = responseEntity.getBody();

        ResponseEntity<OrganizationDto> organizationDtoResponseEntity = organizationServiceAPIClient.getOrganization(employee.getOrganizationCode());
        OrganizationDto organizationDto = organizationDtoResponseEntity.getBody();

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(EmployeeDto.of(employee));
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setOrganizationDto(organizationDto);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception e) {
        LOGGER.info("call getDefaultDepartment");

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