package com.fetrova.accounts

import com.fetrova.accounts.documentation.AccountsAppDocumentation
import com.fetrova.accounts.dto.AccountsContactInfoDto
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@EnableConfigurationProperties(AccountsContactInfoDto::class)
@AccountsAppDocumentation
class AccountsApplication

fun main(args: Array<String>) {
    runApplication<AccountsApplication>(*args)
}
