package com.fetrova.accounts.repository

import com.fetrova.accounts.entity.Accounts
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface AccountRepository : JpaRepository<Accounts, Long> {

    fun findByCustomerId(customerId: Long): Accounts?

    @Transactional
    @Modifying
    fun deleteByCustomerId(customerId: Long)
}