package com.fetrova.cards.dto

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cards")
class CardsContactInfoDto {
    var message: String? = null
    var contactDetails: Map<String, String>? = null
    var onCallSupport: List<String>? = null
}