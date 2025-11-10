package com.fetrova.accounts.service.impl

import com.fetrova.accounts.dto.AccountDTO
import com.fetrova.accounts.dto.CardsDTO
import com.fetrova.accounts.dto.CustomerDetailsDTO
import com.fetrova.accounts.dto.LoansDTO
import com.fetrova.accounts.entity.Accounts
import com.fetrova.accounts.exception.ResourceNotFoundException
import com.fetrova.accounts.mapper.mapToAccountsDto
import com.fetrova.accounts.mapper.mapToCustomerDetails
import com.fetrova.accounts.repository.AccountRepository
import com.fetrova.accounts.repository.CustomerRepository
import com.fetrova.accounts.service.ICustomersService
import com.fetrova.accounts.service.client.CardsFeignClient
import com.fetrova.accounts.service.client.LoansFeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CustomersServiceImpl(
    private val accountsRepository: AccountRepository,
    private val customerRepository: CustomerRepository,
    private val cardsFeignClient: CardsFeignClient,
    private val loansFeignClient: LoansFeignClient
): ICustomersService {

    override fun fetchCustomerDetails(correlationId: String, mobileNumber: String): CustomerDetailsDTO {
        val customer = customerRepository.findByMobileNumber(mobileNumber) ?: throw ResourceNotFoundException(
            "Customer",
            "mobileNumber",
            mobileNumber
        )

        val account: Accounts = customer.customerId?.let { accountsRepository.findByCustomerId(it) }
            ?: throw ResourceNotFoundException("Account", "customerId", customer.customerId.toString())

        val customerDetails = customer.mapToCustomerDetails(CustomerDetailsDTO())
        customerDetails.accountDTO = account.mapToAccountsDto(AccountDTO())

        // Open feign
        val cardsDTOResponseEntity: ResponseEntity<CardsDTO> = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber)
        customerDetails.cards = cardsDTOResponseEntity.body

        val loansDTOResponseEntity: ResponseEntity<LoansDTO> = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber)
        customerDetails.loans = loansDTOResponseEntity.body

        return customerDetails
    }
}