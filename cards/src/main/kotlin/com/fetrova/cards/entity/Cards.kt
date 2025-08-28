package com.fetrova.cards.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GeneratorType

@Entity
@Table(name = "cards")
class Cards(
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var cardId: Long? = null,

    @Column(name = "mobile_number")
    var mobileNumber: String? = null,

    @Column(name = "card_number")
    var cardNumber: String? = null,

    @Column(name = "card_type")
    var cardType: String? = null,

    @Column(name = "total_limit")
    var totalLimit: Int? = null,

    @Column(name = "amount_used")
    var amountUsed: Int? = null,

    @Column(name = "available_amount")
    var availableAmount: Int? = null

) : BaseEntity() {
    override fun toString(): String {
        return "Cards(cardId=$cardId, mobileNumber=$mobileNumber, cardNumber=$cardNumber, cardType=$cardType, " +
                "totalLimit=$totalLimit, amountUsed=$amountUsed, availableAmount=$availableAmount)"
    }
}