package com.fetrova.cards.service

import com.fetrova.cards.dto.CardsDTO


interface ICardsService {

    fun createCards(mobileNumber: String)

    fun fetchCard(mobileNumber: String): CardsDTO?

    fun updateCards(cardsDTO: CardsDTO): Boolean

    fun deleteCards(mobileNumber: String): Boolean

}