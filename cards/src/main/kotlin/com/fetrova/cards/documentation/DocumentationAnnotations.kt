package com.fetrova.cards.documentation

import com.fetrova.cards.constants.MESSAGE_200
import com.fetrova.cards.constants.MESSAGE_201
import com.fetrova.cards.constants.MESSAGE_417_DELETE
import com.fetrova.cards.constants.MESSAGE_417_UPDATE
import com.fetrova.cards.constants.MESSAGE_500
import com.fetrova.cards.constants.STATUS_200
import com.fetrova.cards.constants.STATUS_201
import com.fetrova.cards.constants.STATUS_417
import com.fetrova.cards.constants.STATUS_500
import com.fetrova.cards.dto.ErrorResponseDTO
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
@OpenAPIDefinition(
    info = Info(
        title = "Cards microservice REST API Documentation",
        description = "Cards microservice REST API",
        version = "1.0",
        contact = Contact(
            name = "Felipe Araujo",
            url = "https://fetrova.com",
            email = "hello@felipetrova.com"
        )
    ),
    externalDocs = ExternalDocumentation(
        description = "Cards microservice source code",
        url = "https://github.com/feharaujo/bank-microservices"
    )
)
annotation class CardsAppDocumentation

@Target(CLASS)
@Validated
@Tag(
    name = "APIs for Cards",
    description = "CRUD REST APIs for Cards"
)
annotation class CardsControllerDocumentation

@Target(AnnotationTarget.FUNCTION)
@Operation(
    summary = "Create a new Card",
    description = "Creates a new customer Card"
)
@ApiResponse(responseCode = STATUS_201, description = MESSAGE_201)
annotation class CardCreationDocumentation

@Target(AnnotationTarget.FUNCTION)
@Operation(
    summary = "Fetch Card details",
    description = "Fetch Card details for a given mobile number"
)
@ApiResponse(responseCode = STATUS_201, description = MESSAGE_201)
annotation class CardFetchDocumentation

@Target(AnnotationTarget.FUNCTION)
@Operation(
    summary = "Update Card details",
    description = "Update Card details for a given mobile number"
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
annotation class CardUpdateDocumentation

@Target(AnnotationTarget.FUNCTION)
@Operation(
    summary = "Delete Card details",
    description = "Delete Card details for a given mobile number"
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
annotation class CardDeleteDocumentation