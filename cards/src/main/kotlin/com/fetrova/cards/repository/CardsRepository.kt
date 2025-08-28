package com.fetrova.cards.repository

import com.fetrova.cards.entity.Cards
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardsRepository : JpaRepository<Cards, Long> {
    fun findByCardNumber(mobileNumber: String): Cards?
    fun findByMobileNumber(mobileNumber: String): Cards?
}