package com.fetrova.cards.exception

import com.fetrova.cards.dto.ErrorResponseDTO
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<in Any>? {
        val validationErrorsList = ex.bindingResult.fieldErrors.associate { it.field to it.defaultMessage }
        return ResponseEntity.badRequest().body(validationErrorsList)
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(
        exception: Exception,
        webRequest: WebRequest
    ): ResponseEntity<ErrorResponseDTO> {
        val errorResponseDTO = ErrorResponseDTO(
            webRequest.getDescription(false),
            HttpStatus.INTERNAL_SERVER_ERROR,
            exception.message ?: "-",
            LocalDateTime.now()
        )

        return ResponseEntity(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(
        exception: ResourceNotFoundException,
        webRequest: WebRequest
    ): ResponseEntity<ErrorResponseDTO> {
        val errorResponseDTO = ErrorResponseDTO(
            webRequest.getDescription(false),
            HttpStatus.NOT_FOUND,
            exception.message ?: "-",
            LocalDateTime.now()
        )

        return ResponseEntity(errorResponseDTO, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(CardAlreadyExistsException::class)
    fun handleCustomerAlreadyExistsException(
        exception: CardAlreadyExistsException,
        webRequest: WebRequest
    ): ResponseEntity<ErrorResponseDTO> {
        val errorResponseDTO = ErrorResponseDTO(
            webRequest.getDescription(false),
            HttpStatus.BAD_REQUEST,
            exception.message,
            LocalDateTime.now()
        )

        return ResponseEntity(errorResponseDTO, HttpStatus.BAD_REQUEST)
    }

}