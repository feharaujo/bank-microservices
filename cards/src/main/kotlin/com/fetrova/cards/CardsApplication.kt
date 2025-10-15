package com.fetrova.cards

import com.fetrova.cards.documentation.CardsAppDocumentation
import com.fetrova.cards.dto.CardsContactInfoDto
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@EnableConfigurationProperties(CardsContactInfoDto::class)
@CardsAppDocumentation
class CardsApplication

fun main(args: Array<String>) {
    runApplication<CardsApplication>(*args)
}