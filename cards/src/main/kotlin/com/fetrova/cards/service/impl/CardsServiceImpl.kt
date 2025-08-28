package com.fetrova.cards.service.impl

import com.fetrova.cards.constants.CREDIT_CARD
import com.fetrova.cards.constants.NEW_CARD_LIMIT
import com.fetrova.cards.dto.CardsDTO
import com.fetrova.cards.entity.Cards
import com.fetrova.cards.exception.CardAlreadyExistsException
import com.fetrova.cards.exception.ResourceNotFoundException
import com.fetrova.cards.mapper.mapToCards
import com.fetrova.cards.mapper.mapToCardsDTO
import com.fetrova.cards.repository.CardsRepository
import com.fetrova.cards.service.ICardsService
import org.springframework.stereotype.Service
import java.util.Random

@Service
class CardsServiceImpl(
    private val cardsRepository: CardsRepository
): ICardsService {
    override fun createCards(mobileNumber: String) {
        if(cardsRepository.findByMobileNumber(mobileNumber) != null)
            throw CardAlreadyExistsException("Cards already exist for mobile number $mobileNumber")

        cardsRepository.save(createNewCard(mobileNumber = mobileNumber))

    }

    override fun fetchCard(mobileNumber: String): CardsDTO? {
        val card = cardsRepository.findByMobileNumber(mobileNumber) ?: throw ResourceNotFoundException(
            "Card",
            "mobileNumber",
            mobileNumber
        )
        return card.mapToCardsDTO()
    }

    override fun updateCards(cardsDTO: CardsDTO): Boolean {
        val card = cardsDTO.cardNumber?.let { cardsRepository.findByCardNumber(it) }
            ?: throw ResourceNotFoundException("Card", "cardNumber", cardsDTO.cardNumber ?: "-")
        val updatedCard = cardsDTO.mapToCards(card)
        cardsRepository.save(updatedCard)
        return true
    }

    override fun deleteCards(mobileNumber: String): Boolean {
        val card = cardsRepository.findByMobileNumber(mobileNumber) ?: throw ResourceNotFoundException(
            "Card",
            "mobileNumber",
            mobileNumber
        )
        cardsRepository.delete(card)
        return true
    }

    private fun createNewCard(mobileNumber: String?): Cards {
        val newCard = Cards()
        val randomCardNumber: Long = 100000000000L + Random().nextInt(900000000)
        newCard.cardNumber = randomCardNumber.toString()
        newCard.mobileNumber = mobileNumber
        newCard.cardType = CREDIT_CARD
        newCard.totalLimit = NEW_CARD_LIMIT
        newCard.amountUsed = 0
        newCard.availableAmount = NEW_CARD_LIMIT
        return newCard
    }
}