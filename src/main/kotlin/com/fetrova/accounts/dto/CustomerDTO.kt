package com.fetrova.accounts.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

@Schema(
    name = "Customer",
    description = "Customer and Account information"
)
class CustomerDTO(

    @field:Schema(description = "Customer name", example = "Felipe Trova de Araujo")
    @field:NotEmpty(message = "Name cannot be null or empty")
    @field:Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    var name: String? = null,

    @field:Schema(description = "Customer email", example = "example@test.com")
    @field:NotEmpty(message = "Email cannot be null or empty")
    @field:Email(message = "Email must be a valid email address")
    var email: String? = null,

    @field:Schema(description = "Customer mobile number", example = "0610001122")
    @field:NotEmpty(message = "Mobile number cannot be null or empty")
    @field:Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be a valid phone number")
    var mobileNumber: String? = null,

    @field:Schema(description = "Customer account information")
    var accountDTO: AccountDTO? = null
) {
    override fun toString(): String {
        return "CustomerDTO(name=$name, email=$email, mobileNumber=$mobileNumber)"
    }
}