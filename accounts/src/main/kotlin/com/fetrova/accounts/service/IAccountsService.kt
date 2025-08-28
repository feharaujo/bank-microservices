package com.fetrova.accounts.service

import com.fetrova.accounts.dto.CustomerDTO

interface IAccountsService {

    fun createAccount(customerDTO: CustomerDTO)

    fun fetchAccount(mobileNumber: String): CustomerDTO

    fun updateAccount(newCustomerDTO: CustomerDTO): Boolean

    fun deleteAccount(mobileNumber: String): Boolean
}