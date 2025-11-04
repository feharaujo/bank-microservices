package com.fetrova.accounts.controller

import com.fetrova.accounts.documentation.CustomerControllerDocumentation
import com.fetrova.accounts.documentation.CustomerFetchDocumentation
import com.fetrova.accounts.dto.CustomerDetailsDTO
import com.fetrova.accounts.service.ICustomersService
import jakarta.validation.constraints.Pattern
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
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

    @CustomerFetchDocumentation
    @GetMapping("/fetchCustomerDetails")
    fun fetchCustomerDetails(
        @RequestParam @Pattern(
            regexp = "(^$|[0-9]{10})",
            message = "Mobile number must be a valid phone number"
        ) mobileNumber: String
    ): ResponseEntity<CustomerDetailsDTO> {
        val customerDetails = customerService.fetchCustomerDetails(mobileNumber)
        return ResponseEntity.ok(customerDetails)
    }

}