package com.fetrova.accounts.controller

import com.fetrova.accounts.documentation.AccountCreationDocumentation
import com.fetrova.accounts.documentation.AccountFetchDocumentation
import com.fetrova.accounts.documentation.AccountUpdateDocumentation
import com.fetrova.accounts.documentation.AccountsControllerDocumentation
import com.fetrova.accounts.constants.MESSAGE_200
import com.fetrova.accounts.constants.MESSAGE_201
import com.fetrova.accounts.constants.MESSAGE_417_DELETE
import com.fetrova.accounts.constants.MESSAGE_417_UPDATE
import com.fetrova.accounts.constants.STATUS_200
import com.fetrova.accounts.constants.STATUS_201
import com.fetrova.accounts.constants.STATUS_417
import com.fetrova.accounts.documentation.AccountDeleteDocumentation
import com.fetrova.accounts.documentation.BuildInfoDocumentation
import com.fetrova.accounts.documentation.ContactInfoDocumentation
import com.fetrova.accounts.documentation.JavaVersionDocumentation
import com.fetrova.accounts.dto.AccountsContactInfoDto
import com.fetrova.accounts.dto.CustomerDTO
import com.fetrova.accounts.dto.ResponseDTO
import com.fetrova.accounts.service.IAccountsService
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
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

@AccountsControllerDocumentation
@RestController
@RequestMapping(
    path = ["/api"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class AccountsController(
    var accountService: IAccountsService
) {

    @Value("\${build.version}")
    lateinit var buildVersion: String

    @Autowired
    lateinit var environment: Environment

    @Autowired
    lateinit var accountsContactInfo: AccountsContactInfoDto

    @AccountCreationDocumentation
    @PostMapping("/create")
    fun createAccount(@Valid @RequestBody customerDTO: CustomerDTO): ResponseEntity<ResponseDTO> {
        accountService.createAccount(customerDTO)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ResponseDTO(STATUS_201, MESSAGE_201))
    }

    @AccountFetchDocumentation
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

    @AccountUpdateDocumentation
    @PutMapping("/update")
    fun updateAccountDetails(@Valid @RequestBody customerDTO: CustomerDTO): ResponseEntity<ResponseDTO> {
        val isUpdated = accountService.updateAccount(customerDTO)
        return if (isUpdated) {
            ResponseEntity.ok(ResponseDTO(STATUS_200, MESSAGE_200))
        } else {
            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDTO(STATUS_417, MESSAGE_417_UPDATE))
        }
    }

    @AccountDeleteDocumentation
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

    @BuildInfoDocumentation
    @GetMapping("/build-info")
    fun getBuildInfo(): ResponseEntity<String> {
        return ResponseEntity.ok(buildVersion)
    }

    @JavaVersionDocumentation
    @GetMapping("/java-version")
    fun getJavaVersion(): ResponseEntity<String> {
        return ResponseEntity.ok(environment.getProperty("JAVA_HOME"))
    }

    @ContactInfoDocumentation
    @GetMapping("/contact-info")
    fun getContactInfo(): ResponseEntity<AccountsContactInfoDto> {
        return ResponseEntity.ok(accountsContactInfo)
    }

}