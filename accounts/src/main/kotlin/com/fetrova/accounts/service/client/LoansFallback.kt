package com.fetrova.accounts.service.client

import com.fetrova.accounts.dto.LoansDTO
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class LoansFallback : LoansFeignClient {
    override fun fetchLoanDetails(
        correlationId: String,
        mobileNumber: String
    ): ResponseEntity<LoansDTO>? {
        return null
    }
}