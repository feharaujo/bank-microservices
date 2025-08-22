package com.fetrova.accounts.controlller

import com.fetrova.accounts.constants.MESSAGE_200
import com.fetrova.accounts.constants.MESSAGE_201
import com.fetrova.accounts.constants.MESSAGE_417_DELETE
import com.fetrova.accounts.constants.MESSAGE_417_UPDATE
import com.fetrova.accounts.constants.MESSAGE_500
import com.fetrova.accounts.constants.STATUS_200
import com.fetrova.accounts.constants.STATUS_201
import com.fetrova.accounts.constants.STATUS_417
import com.fetrova.accounts.constants.STATUS_500
import com.fetrova.accounts.dto.CustomerDTO
import com.fetrova.accounts.dto.ErrorResponseDTO
import com.fetrova.accounts.dto.ResponseDTO
import com.fetrova.accounts.service.IAccountsService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
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
@Validated
@Tag(
    name = "APIs for Accounts",
    description = "CRUD REST APIs for Accounts"
)
class AccountsController(
    var accountService: IAccountsService
) {

    @Operation(summary = "Create a new account", description = "Creates a new customer account")
    @ApiResponse(responseCode = STATUS_201, description = MESSAGE_201)
    @PostMapping("/create")
    fun createAccount(@Valid @RequestBody customerDTO: CustomerDTO): ResponseEntity<ResponseDTO> {
        accountService.createAccount(customerDTO)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ResponseDTO(STATUS_201, MESSAGE_201))
    }

    @Operation(summary = "Fetch account details", description = "Fetch account details for a given mobile number")
    @ApiResponse(responseCode = STATUS_201, description = MESSAGE_201)
    @GetMapping("/fetch")
    fun fetchAccountDetails(
        @RequestParam @Pattern(
            regexp = "(^$|[0-9]{10})",
            message = "Mobile number must be a valid phone number"
        ) mobileNumber: String
    ): ResponseEntity<CustomerDTO> {
        val customerDTO = accountService.fetchAccount(mobileNumber)
        return ResponseEntity.ok(customerDTO)
    }

    @Operation(
        summary = "Update account details",
        description = "Update account details for a given mobile number"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = STATUS_200, description = MESSAGE_200),
            ApiResponse(
                responseCode = STATUS_417,
                description = MESSAGE_417_UPDATE,
            ),
            ApiResponse(
                responseCode = STATUS_500,
                description = MESSAGE_500,
                content = [Content(schema = Schema(implementation = ErrorResponseDTO::class))]
            )
        ]
    )
    @PutMapping("/update")
    fun updateAccountDetails(@Valid @RequestBody customerDTO: CustomerDTO): ResponseEntity<ResponseDTO> {
        val isUpdated = accountService.updateAccount(customerDTO)
        return if (isUpdated) {
            ResponseEntity.ok(ResponseDTO(STATUS_200, MESSAGE_200))
        } else {
            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDTO(STATUS_417, MESSAGE_417_UPDATE))
        }
    }

    @Operation(
        summary = "Delete account details",
        description = "Delete account details for a given mobile number"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = STATUS_200, description = MESSAGE_200),
            ApiResponse(responseCode = STATUS_417, description = MESSAGE_417_DELETE),
            ApiResponse(
                responseCode = STATUS_500,
                description = MESSAGE_500,
                content = [Content(schema = Schema(implementation = ErrorResponseDTO::class))]
            )
        ]
    )
    @DeleteMapping("/delete")
    fun deleteAccountDetails(
        @RequestParam @Pattern(
            regexp = "(^$|[0-9]{10})",
            message = "Mobile number must be a valid phone number"
        ) mobileNumber: String
    ): ResponseEntity<ResponseDTO> {
        val isDeleted = accountService.deleteAccount(mobileNumber)
        return if (isDeleted) {
            ResponseEntity.ok(ResponseDTO(STATUS_200, STATUS_200))
        } else {
            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDTO(STATUS_417, MESSAGE_417_DELETE))
        }
    }

}