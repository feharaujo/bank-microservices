package com.fetrova.accounts.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class CustomerAlreadyExistsException(
     message: String
): RuntimeException(message)

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException(
    resourceName: String, fieldName: String, fieldValue: String,
): RuntimeException("$resourceName not found with $fieldName: $fieldValue")
