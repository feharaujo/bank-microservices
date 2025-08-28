package com.fetrova.accounts.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@Schema(
    name = "ErrorResponse",
    description = "Error response information"
)
class ErrorResponseDTO(
    @field:Schema(description = "API path", example = "/api/v1/accounts")
    var apiPath: String,
    @field:Schema(description = "Error code", example = "404")
    var errorCode: HttpStatus,
    @field:Schema(description = "Error message", example = "Customer not found")
    var errorMessage: String,
    @field:Schema(description = "Error time", example = "2023-09-24T10:15:30")
    var errorTime: LocalDateTime,
) {
    override fun toString(): String {
        return "ErrorResponseDTO(apiPath=$apiPath, errorCode=$errorCode, errorMessage=$errorMessage, errorTime=$errorTime)"
    }
}