package com.fetrova.accounts.controller

import com.fetrova.accounts.documentation.CustomerControllerDocumentation
import com.fetrova.accounts.documentation.CustomerFetchDocumentation
import com.fetrova.accounts.dto.CustomerDetailsDTO
import com.fetrova.accounts.service.ICustomersService
import jakarta.validation.constraints.Pattern
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    path = ["/api"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@CustomerControllerDocumentation
class CustomerController(
    private val customerService: ICustomersService
) {

    val logger = LoggerFactory.getLogger(CustomerController::class.java)

    @CustomerFetchDocumentation
    @GetMapping("/fetchCustomerDetails")
    fun fetchCustomerDetails(
        @RequestHeader("bank-correlation-id") correlationId: String,
        @RequestParam @Pattern(
            regexp = "(^$|[0-9]{10})",
            message = "Mobile number must be a valid phone number"
        ) mobileNumber: String
    ): ResponseEntity<CustomerDetailsDTO> {
        //logger.debug("fetchCustomerDetails: correlationId=$correlationId")
        logger.debug("fetchCustomerDetails: started")
        val customerDetails = customerService.fetchCustomerDetails(correlationId, mobileNumber)
        logger.debug("fetchCustomerDetails: ended")

        return ResponseEntity.ok(customerDetails)
    }

}