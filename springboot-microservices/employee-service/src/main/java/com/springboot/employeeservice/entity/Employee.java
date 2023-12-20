package com.springboot.employeeservice.entity;

import com.springboot.employeeservice.dto.EmployeeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;

    private static ModelMapper mapper = new ModelMapper();
    public static Employee of(EmployeeDto employeeDto){
        return mapper.map(employeeDto,Employee.class);
    }
}