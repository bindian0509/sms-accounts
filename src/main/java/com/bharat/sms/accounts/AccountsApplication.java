package com.bharat.sms.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "Fake Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Bharat Verma",
						email = "bindian0509@gmail.com",
						url = "https://linktr.ee/bharatv"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://linktr.ee/bharatv"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Fake Accounts microservice REST API Documentation",
				url = "https://www.eazybytes.com/swagger-ui.html"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}