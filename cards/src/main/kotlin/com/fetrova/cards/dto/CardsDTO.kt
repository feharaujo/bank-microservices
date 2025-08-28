package com.fetrova.cards.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero

@Schema(
    name = "Card",
    description = "Card information"
)
class CardsDTO(

    @field:NotEmpty(message = "Mobile Number can not be a null or empty")
    @field:Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    @field:Schema(description = "Mobile Number of Customer", example = "4354437687")
    var mobileNumber: String? = null,

    @field:NotEmpty(message = "Card Number can not be a null or empty")
    @field:Pattern(regexp = "(^$|[0-9]{12})", message = "CardNumber must be 12 digits")
    @field:Schema(description = "Card Number of the customer", example = "100646930341")
    var cardNumber: String? = null,

    @field:NotEmpty(message = "CardType can not be a null or empty")
    @field:Schema(description = "Type of the card", example = "Credit Card")
    var cardType: String? = null,

    @field:Positive(message = "Total card limit should be greater than zero")
    @field:Schema(description = "Total amount limit available against a card", example = "100000")
    var totalLimit: Int? = null,

    @field:PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    @field:Schema(description = "Total amount used by a Customer", example = "1000")
    var amountUsed: Int? = null,

    @field:PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    @field:Schema(description = "Total available amount against a card", example = "90000")
    var availableAmount: Int? = null,

)