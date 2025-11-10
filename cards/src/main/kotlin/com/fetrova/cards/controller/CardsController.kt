package com.fetrova.cards.controller

import com.fetrova.cards.constants.*
import com.fetrova.cards.documentation.*
import com.fetrova.cards.dto.CardsContactInfoDto
import com.fetrova.cards.dto.CardsDTO
import com.fetrova.cards.dto.ResponseDTO
import com.fetrova.cards.service.ICardsService
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    val logger: Logger = LoggerFactory.getLogger(CardsController::class.java)

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
        @RequestHeader("bank-correlation-id") correlationId: String,
        @Valid @RequestParam
        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") mobileNumber: String
    ): ResponseEntity<CardsDTO> {
        logger.debug("fetchCustomerDetails: correlationId=$correlationId")

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