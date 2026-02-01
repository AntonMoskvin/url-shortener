package ru.moskvin.urlshortener.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ShortenRequest(
    @field:NotBlank
    @field:Size(max = 2048)
    val url: String
)