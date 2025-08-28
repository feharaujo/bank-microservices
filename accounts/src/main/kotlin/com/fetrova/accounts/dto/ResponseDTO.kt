package com.fetrova.accounts.dto

import io.swagger.v3.oas.annotations.media.Schema


@Schema(
    name = "Response",
    description = "Successful response object"
)
class ResponseDTO(
    @field:Schema(description = "Status code")
    var statusCode: String? = null,

    @field:Schema(description = "Status message")
    var statusMsg: String? = null
) {
    override fun toString(): String {
        return "ResponseDTO(statusCode=$statusCode, statusMsg=$statusMsg)"
    }
}