package com.easybytes.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Account microservice REST API documentation",
                description = "EasyBank Account microservice REST API documentation",
                version = "v1",
                contact = @Contact(name = "Joao Vitor", email = "joao@email.com", url = "localhost.com"),
                license = @License(name = "Apache 2.0", url = "localhost.com")
        ),
        externalDocs = @ExternalDocumentation(description = "Other references", url = "other-references.com")
)
@SpringBootApplication
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
