package com.fetrova.accounts.service.client

import com.fetrova.accounts.dto.CardsDTO
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class CardsFallback : CardsFeignClient {
    override fun fetchCardDetails(
        correlationId: String,
        mobileNumber: String
    ): ResponseEntity<CardsDTO>? {
        return null
    }
}