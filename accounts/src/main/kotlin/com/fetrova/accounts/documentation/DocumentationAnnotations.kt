package com.fetrova.accounts.documentation

import com.fetrova.accounts.constants.MESSAGE_200
import com.fetrova.accounts.constants.MESSAGE_201
import com.fetrova.accounts.constants.MESSAGE_417_DELETE
import com.fetrova.accounts.constants.MESSAGE_417_UPDATE
import com.fetrova.accounts.constants.MESSAGE_500
import com.fetrova.accounts.constants.STATUS_200
import com.fetrova.accounts.constants.STATUS_201
import com.fetrova.accounts.constants.STATUS_417
import com.fetrova.accounts.constants.STATUS_500
import com.fetrova.accounts.dto.ErrorResponseDTO
import io.swagger.v3.oas.annotations.ExternalDocumentation
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

@Target(CLASS)
@Retention(RUNTIME)
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
        url = "https://github.com/feharaujo/bank-microservices"
    )
)
annotation class AccountsAppDocumentation

@Target(CLASS)
@Retention(RUNTIME)
@Validated
@Tag(
    name = "APIs for Accounts",
    description = "CRUD REST APIs for Accounts"
)
annotation class AccountsControllerDocumentation

@Target(AnnotationTarget.FUNCTION)
@Retention(RUNTIME)
@Operation(
    summary = "Create a new account",
    description = "Creates a new customer account"
)
@ApiResponse(responseCode = STATUS_201, description = MESSAGE_201)
annotation class AccountCreationDocumentation

@Target(AnnotationTarget.FUNCTION)
@Retention(RUNTIME)
@Operation(
    summary = "Fetch account details",
    description = "Fetch account details for a given mobile number"
)
@ApiResponse(responseCode = STATUS_201, description = MESSAGE_201)
annotation class AccountFetchDocumentation

@Target(AnnotationTarget.FUNCTION)
@Retention(RUNTIME)
@Operation(
    summary = "Update account details",
    description = "Update account details for a given mobile number"
)
@ApiResponses(
    value = [
        ApiResponse(responseCode = STATUS_200, description = MESSAGE_200),
        ApiResponse(
            responseCode = STATUS_417,
            description = MESSAGE_417_UPDATE,
        ),
        ApiResponse(
            responseCode = STATUS_500,
            description = MESSAGE_500,
            content = [Content(schema = Schema(implementation = ErrorResponseDTO::class))]
        )
    ]
)
annotation class AccountUpdateDocumentation

@Target(AnnotationTarget.FUNCTION)
@Retention(RUNTIME)
@Operation(
    summary = "Delete account details",
    description = "Delete account details for a given mobile number"
)
@ApiResponses(
    value = [
        ApiResponse(responseCode = STATUS_200, description = MESSAGE_200),
        ApiResponse(responseCode = STATUS_417, description = MESSAGE_417_DELETE),
        ApiResponse(
            responseCode = STATUS_500,
            description = MESSAGE_500,
            content = [Content(schema = Schema(implementation = ErrorResponseDTO::class))]
        )
    ]
)
annotation class AccountDeleteDocumentation