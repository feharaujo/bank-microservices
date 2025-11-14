package com.fetrova.accounts.service.client

import com.fetrova.accounts.dto.CardsDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

//@FeignClient(name = "cards", url = "\${cards.url}")
@FeignClient(name = "cards")
interface CardsFeignClient {

    // it calls fetchCardDetails method in cards microservice
    @GetMapping("/api/fetch", consumes = ["application/json"], produces = ["application/json"])
    fun fetchCardDetails(
        @RequestHeader("bank-correlation-id") correlationId: String,
        @RequestParam("mobileNumber") mobileNumber: String
    ): ResponseEntity<CardsDTO>

}