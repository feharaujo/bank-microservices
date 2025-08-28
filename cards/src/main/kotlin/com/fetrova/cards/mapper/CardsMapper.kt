package com.fetrova.cards.mapper

import com.fetrova.cards.dto.CardsDTO
import com.fetrova.cards.entity.Cards

fun CardsDTO.mapToCards(cards: Cards = Cards()): Cards {
    cards.mobileNumber = this.mobileNumber
    cards.cardNumber = this.cardNumber
    cards.cardType = this.cardType
    cards.totalLimit = this.totalLimit
    cards.amountUsed = this.amountUsed
    cards.availableAmount = this.availableAmount
    return cards
}

fun Cards.mapToCardsDTO(cardsDTO: CardsDTO = CardsDTO()): CardsDTO {
    cardsDTO.mobileNumber = this.mobileNumber
    cardsDTO.cardNumber = this.cardNumber
    cardsDTO.cardType = this.cardType
    cardsDTO.totalLimit = this.totalLimit
    cardsDTO.amountUsed = this.amountUsed
    cardsDTO.availableAmount = this.availableAmount
    return cardsDTO
}