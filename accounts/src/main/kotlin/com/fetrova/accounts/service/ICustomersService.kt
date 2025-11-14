package com.fetrova.accounts.service

import com.fetrova.accounts.dto.CustomerDetailsDTO

interface ICustomersService {

    fun fetchCustomerDetails(correlationId: String, mobileNumber: String): CustomerDetailsDTO

}