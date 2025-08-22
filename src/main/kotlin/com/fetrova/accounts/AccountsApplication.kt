package com.fetrova.accounts

import io.swagger.v3.oas.annotations.ExternalDocumentation
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@OpenAPIDefinition(
    info = Info(
        title = "Accounts microservice REST API Documentation",
        description = "Accounts microservice REST API",
        version = "1.0",
        contact = Contact(
            name = "Felipe Araujo",
            url = "https://fetrova.com",
            email = "hello@felipetrova.com"
        )
    ),
    externalDocs = ExternalDocumentation(
        description = "Accounts microservice source code",
        url = "https://github.com/feharaujo/accounts-microservices"
    )
)
class AccountsApplication

fun main(args: Array<String>) {
    runApplication<AccountsApplication>(*args)
}
