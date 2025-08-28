package com.fetrova.accounts.service.impl

import com.fetrova.accounts.constants.ADDRESS
import com.fetrova.accounts.constants.SAVINGS
import com.fetrova.accounts.dto.AccountDTO
import com.fetrova.accounts.dto.CustomerDTO
import com.fetrova.accounts.entity.Accounts
import com.fetrova.accounts.entity.Customer
import com.fetrova.accounts.exception.CustomerAlreadyExistsException
import com.fetrova.accounts.exception.ResourceNotFoundException
import com.fetrova.accounts.mapper.mapToAccounts
import com.fetrova.accounts.mapper.mapToAccountsDto
import com.fetrova.accounts.mapper.mapToCustomer
import com.fetrova.accounts.mapper.mapToCustomerDto
import com.fetrova.accounts.repository.AccountRepository
import com.fetrova.accounts.repository.CustomerRepository
import com.fetrova.accounts.service.IAccountsService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.Random

@Service
class AccountsServiceImpl(
    val accountsRepository: AccountRepository,
    val customerRepository: CustomerRepository
) : IAccountsService {

    override fun createAccount(customerDTO: CustomerDTO) {
        val customer = customerDTO.mapToCustomer(Customer())

        val mobileNumber = customer.mobileNumber ?: throw RuntimeException("Mobile number is required")
        customerRepository.findByMobileNumber(mobileNumber)?.let {
            throw CustomerAlreadyExistsException("Customer with phone number $mobileNumber already exists")
        }

        val savedCustomer = customerRepository.save(customer)
        val randomAccountNumber = 1000000000L + Random().nextInt(900000000)
        accountsRepository.save(
            Accounts(
                customerId = savedCustomer.customerId,
                accountNumber = randomAccountNumber,
                accountType = SAVINGS,
                branchAddress = ADDRESS,
            )
        )
    }

    override fun fetchAccount(mobileNumber: String): CustomerDTO {
        val customer = customerRepository.findByMobileNumber(mobileNumber) ?: throw ResourceNotFoundException(
            "Customer",
            "mobileNumber",
            mobileNumber
        )

        val account: Accounts = customer.customerId?.let { accountsRepository.findByCustomerId(it) }
            ?: throw ResourceNotFoundException("Account", "customerId", customer.customerId.toString())

        return customer.mapToCustomerDto(CustomerDTO()).apply {
            this.accountDTO = account.mapToAccountsDto(AccountDTO())
        }
    }

    override fun updateAccount(newCustomerDTO: CustomerDTO): Boolean {
        val newAccountDTO = newCustomerDTO.accountDTO ?: return false
        val accountId =
            newAccountDTO.accountNumber ?: throw ResourceNotFoundException("Account", "accountNumber", "accountNumber")

        var account = accountsRepository.findById(accountId)
            .orElseThrow {
                ResourceNotFoundException("Account", "accountNumber", accountId.toString())
            }
        account = newAccountDTO.mapToAccounts(account)
        account = accountsRepository.save(account)

        val customerId = account.customerId ?: throw ResourceNotFoundException("Customer", "customerId", "customerId")
        val customer = customerRepository.findById(customerId)
            .orElseThrow {
                ResourceNotFoundException("Customer", "customerId", customerId.toString())
            }

        customerRepository.save(newCustomerDTO.mapToCustomer(customer))

        return true;
    }

    override fun deleteAccount(mobileNumber: String): Boolean {
        val customer = customerRepository.findByMobileNumber(mobileNumber) ?: throw ResourceNotFoundException(
            "Customer",
            "mobileNumber",
            mobileNumber
        )

        accountsRepository.deleteByCustomerId(customer.customerId!!)
        customerRepository.deleteById(customer.customerId!!)
        return true
    }
}