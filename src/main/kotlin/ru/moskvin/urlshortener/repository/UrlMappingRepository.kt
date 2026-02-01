package ru.moskvin.urlshortener.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.moskvin.urlshortener.model.UrlMapping

@Repository
interface UrlMappingRepository : JpaRepository<UrlMapping, Long> {

    fun findByShortCode(shortCode: String): UrlMapping?
}