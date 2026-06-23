package com.ngts.my_first_app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Alan's First APIs",
				version = "1.0",
				description = "API documentation for managing Users"
		)
)
@SpringBootApplication
public class MyFirstAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyFirstAppApplication.class, args);
	}
}
