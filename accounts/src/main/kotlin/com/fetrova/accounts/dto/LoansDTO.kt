package com.fetrova.accounts.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero

@Schema(name = "Loans", description = "Schema to hold Loan information")
class LoansDTO(

    @field:NotEmpty(message = "Mobile Number can not be a null or empty")
    @field:Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    @field:Schema(description = "Mobile Number of Customer", example = "4365327698")
    var mobileNumber: String? = null,

    @field:NotEmpty(message = "Loan Number can not be a null or empty")
    @field:Pattern(regexp="(^$|[0-9]{12})",message = "LoanNumber must be 12 digits")
    @field:Schema(
        description = "Loan Number of the customer", example = "548732457654"
    )
    var loanNumber: String? = null,

    @field:NotEmpty(message = "LoanType can not be a null or empty")
    @field:Schema(
        description = "Type of the loan", example = "Home Loan"
    )
    var loanType: String? = null,

    @field:Positive(message = "Total loan amount should be greater than zero")
    @field:Schema(
        description = "Total loan amount", example = "100000"
    )
    totalLoan: Int? = null,

    @field:PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @field:Schema(
        description = "Total loan amount paid", example = "1000"
    )
    var amountPaid: Int? = null,

    @field:PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @field:Schema(
        description = "Total outstanding amount against a loan", example = "99000"
    )
    var outstandingAmount: Int? = null,
)