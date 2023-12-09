package com.springboot.blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.Inherited;

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
public class SpringbootBlogApplication {

	@Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogApplication.class, args);
	}

}
