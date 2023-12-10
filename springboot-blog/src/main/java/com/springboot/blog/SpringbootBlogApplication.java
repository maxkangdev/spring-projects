package com.springboot.blog;

import com.springboot.blog.entity.Role;
import com.springboot.blog.repository.RoleRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.Inherited;
import java.util.Optional;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Blog App Rest APIs",
				description = "Spring Boot Blog App REST APIs Documentation",
				version = "v1.0.0",
				contact = @Contact(
						name = "Seongwoo Kang",
						email = "maxkangdev@gmail.com",
						url = "https://www.tbd.net"
				),
				license = @License(
						name="Apache 2.0",
						url = "https://w..tbd.net"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "External Doc, Spring Boot Blog App Documentation",
				url = "https://github.com/maxkangdev/spring-projects"
		)
)
public class SpringbootBlogApplication implements CommandLineRunner {

	@Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {

		// insert ROLE_ADMIN to DB application startup
		Optional<Role> roleAdmin = roleRepository.findByName("ROLE_ADMIN");
		if(roleAdmin.isEmpty()){
			Role adminRole = new Role();
			adminRole.setName("ROLE_ADMIN");
			roleRepository.save(adminRole);
		}

		// insert ROLE_ADMIN to DB application startup
		Optional<Role> roleUser = roleRepository.findByName("ROLE_USER");
		if(roleUser.isEmpty()){
			Role userRole = new Role();
			userRole.setName("ROLE_USER");
			roleRepository.save(userRole);
		}
	}
}
