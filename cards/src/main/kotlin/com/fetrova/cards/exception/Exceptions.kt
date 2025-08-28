package com.fetrova.cards.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class CardAlreadyExistsException(override val message: String) : RuntimeException(message)

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException(val resourceName: String, val fieldName: String, fieldValue: String) : RuntimeException(
    "$resourceName not found with $fieldName: $fieldValue"
)