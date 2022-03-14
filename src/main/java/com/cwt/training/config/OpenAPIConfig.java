package com.cwt.training.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

@Configuration
@OpenAPIDefinition(
		info = @Info(title = "Spring Boot Rest API Test", version = "v1"),
		security = @SecurityRequirement(name = "basicAuth")
)
@SecurityScheme(
		name = "basicAuth",
	    type = SecuritySchemeType.HTTP,
	    scheme = "basic"
)
public class OpenAPIConfig {

}
