package com.fetrova.accounts.repository

import com.fetrova.accounts.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {
    fun findByMobileNumber(mobileNumber: String): Customer?
}