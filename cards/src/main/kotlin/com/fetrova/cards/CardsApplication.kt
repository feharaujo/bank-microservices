package com.fetrova.cards

import com.fetrova.cards.documentation.CardsAppDocumentation
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@CardsAppDocumentation
class CardsApplication

fun main(args: Array<String>) {
    runApplication<CardsApplication>(*args)
}