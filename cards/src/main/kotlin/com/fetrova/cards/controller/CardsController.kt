package com.fetrova.cards.controller

import com.fetrova.cards.constants.MESSAGE_200
import com.fetrova.cards.constants.MESSAGE_201
import com.fetrova.cards.constants.MESSAGE_417_DELETE
import com.fetrova.cards.constants.MESSAGE_417_UPDATE
import com.fetrova.cards.constants.STATUS_200
import com.fetrova.cards.constants.STATUS_201
import com.fetrova.cards.constants.STATUS_417
import com.fetrova.cards.documentation.CardCreationDocumentation
import com.fetrova.cards.documentation.CardDeleteDocumentation
import com.fetrova.cards.documentation.CardFetchDocumentation
import com.fetrova.cards.documentation.CardUpdateDocumentation
import com.fetrova.cards.documentation.CardsControllerDocumentation
import com.fetrova.cards.dto.CardsContactInfoDto
import com.fetrova.cards.dto.CardsDTO
import com.fetrova.cards.dto.ResponseDTO
import com.fetrova.cards.service.ICardsService
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    path = ["/api"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@CardsControllerDocumentation
class CardsController(private val cardsService: ICardsService, private val iCardsService: ICardsService) {

    @Value("\${build.version}")
    lateinit var buildVersion: String

    @Autowired
    lateinit var environment: Environment

    @Autowired
    lateinit var cardsContactInfo: CardsContactInfoDto

    @CardCreationDocumentation
    @PostMapping("/create")
    fun createCards(
        @Valid @RequestParam
        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") mobileNumber: String
    ): ResponseEntity<ResponseDTO> {
        iCardsService.createCards(mobileNumber)
        return ResponseEntity.ok(ResponseDTO(STATUS_201, MESSAGE_201))
    }

    @CardFetchDocumentation
    @GetMapping("/fetch")
    fun fetchCardDetails(
        @Valid @RequestParam
        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") mobileNumber: String
    ): ResponseEntity<CardsDTO> {
        val card = cardsService.fetchCard(mobileNumber)
        return ResponseEntity.ok(card)
    }

    @CardUpdateDocumentation
    @PutMapping("/update")
    fun updateCardDetails(@Valid @RequestBody cardsDTO: CardsDTO): ResponseEntity<ResponseDTO> {
        val isUpdated = cardsService.updateCards(cardsDTO)
        return if (isUpdated) {
            ResponseEntity.ok(ResponseDTO(STATUS_200, MESSAGE_200))
        } else {
            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDTO(STATUS_417, MESSAGE_417_UPDATE))
        }
    }

    @CardDeleteDocumentation
    @DeleteMapping("/delete")
    fun deleteCard(
        @Valid @RequestParam
        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") mobileNumber: String
    ): ResponseEntity<ResponseDTO> {
        val isDeleted = cardsService.deleteCards(mobileNumber)
        return if (isDeleted) {
            ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO(STATUS_200, MESSAGE_200))
        } else {
            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(ResponseDTO(STATUS_417, MESSAGE_417_DELETE))
        }
    }

    @GetMapping("/build-info")
    fun getBuildInfo(): ResponseEntity<String> {
        return ResponseEntity.ok(buildVersion)
    }

    @GetMapping("/contact-info")
    fun getContactInfo(): ResponseEntity<CardsContactInfoDto> {
        return ResponseEntity.ok(cardsContactInfo)
    }

}