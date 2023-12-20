package com.springboot.departmentservice.entity;


import com.springboot.departmentservice.dto.DepartmentDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;
    private static ModelMapper mapper = new ModelMapper();
    public static Department of(DepartmentDto departmentDto){
        return mapper.map(departmentDto,Department.class);
    }
}
