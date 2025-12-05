package com.fetrova.accounts.service.client

import com.fetrova.accounts.dto.LoansDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "loans", fallback = LoansFallback::class)
interface LoansFeignClient {

    @GetMapping("/api/fetch", consumes = ["application/json"], produces = ["application/json"])
    fun fetchLoanDetails(
        @RequestHeader("bank-correlation-id") correlationId: String,
        @RequestParam("mobileNumber") mobileNumber: String
    ): ResponseEntity<LoansDTO>?

}