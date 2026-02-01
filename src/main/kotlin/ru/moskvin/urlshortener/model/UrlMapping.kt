package ru.moskvin.urlshortener.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Entity
@Table(name = "url_mappings", indexes = [
    Index(name = "idx_short_code", columnList = "shortCode", unique = true)
])
data class UrlMapping(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:NotBlank
    @field:Size(max = 2048)
    @Column(nullable = false, length = 2048)
    val originalUrl: String,

    @field:NotBlank
    @field:Size(min = 4, max = 10)
    @Column(nullable = false, unique = true, length = 10)
    val shortCode: String
)