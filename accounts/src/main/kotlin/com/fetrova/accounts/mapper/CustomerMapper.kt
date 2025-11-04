package com.fetrova.accounts.mapper

import com.fetrova.accounts.dto.CustomerDTO
import com.fetrova.accounts.dto.CustomerDetailsDTO
import com.fetrova.accounts.entity.Customer


fun Customer.mapToCustomerDto(customerDto: CustomerDTO): CustomerDTO {
    customerDto.name = this.name
    customerDto.email = this.email
    customerDto.mobileNumber = this.mobileNumber
    return customerDto
}

fun CustomerDTO.mapToCustomer(customer: Customer): Customer {
    customer.name = this.name
    customer.email = this.email
    customer.mobileNumber = this.mobileNumber
    return customer
}

fun Customer.mapToCustomerDetails(customerDetailsDTO: CustomerDetailsDTO): CustomerDetailsDTO {
    customerDetailsDTO.name = this.name
    customerDetailsDTO.email = this.email
    customerDetailsDTO.mobileNumber = this.mobileNumber
    return customerDetailsDTO
}