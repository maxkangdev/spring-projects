package com.springboot.organizationservice.entity;

import com.springboot.organizationservice.dto.OrganizationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String organizationName;
    private String organizationDescription;
    @Column(nullable = false, unique = true)
    private String organizationCode;
    @CreationTimestamp
    private LocalDateTime createdDate;


    private static ModelMapper mapper = new ModelMapper();
    public static Organization of(OrganizationDto organizationDto){
        return mapper.map(organizationDto,Organization.class);
    }
}
