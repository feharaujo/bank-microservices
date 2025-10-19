package com.fetrova.accounts.dto

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "accounts")
class AccountsContactInfoDto {
    var message: String? = null
    var contactDetails: Map<String, String>? = null
    var onCallSupport: List<String>? = null
}