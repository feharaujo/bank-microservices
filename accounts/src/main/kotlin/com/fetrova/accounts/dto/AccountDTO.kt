package com.fetrova.accounts.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern

@Schema(
    name = "Account",
    description = "Account information"
)
class AccountDTO(
    @field:Schema(description = "Account number", example = "1234567890")
    @field:NotEmpty(message = "Account number cannot be null or empty")
    @field:Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    var accountNumber: Long? = null,

    @field:Schema(description = "Account type", example = "Current")
    @field:NotEmpty(message = "Account type cannot be null or empty")
    var accountType: String? = null,

    @field:Schema(description = "Branch address", example = "123 Main St, Apt 123")
    @field:NotEmpty(message = "Branch address cannot be null or empty")
    var branchAddress: String? = null
) {
    override fun toString(): String {
        return "AccountsDTO(accountNumber=$accountNumber, accountType=$accountType, branchAddress=$branchAddress)"
    }
}