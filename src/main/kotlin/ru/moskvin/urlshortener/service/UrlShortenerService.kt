package ru.moskvin.urlshortener.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.moskvin.urlshortener.model.UrlMapping
import ru.moskvin.urlshortener.repository.UrlMappingRepository
import ru.moskvin.urlshortener.util.ShortCodeGenerator

@Service
class UrlShortenerService(
    private val repository: UrlMappingRepository
) {

    @Transactional
    fun createShortUrl(originalUrl: String): String {
        // Проверим, не существует ли уже запись с таким originalUrl (опционально)
        // Но для MVP будем генерировать новый shortCode каждый раз

        var shortCode: String
        do {
            shortCode = ShortCodeGenerator.generate()
        } while (repository.findByShortCode(shortCode) != null)

        val urlMapping = UrlMapping(
            originalUrl = originalUrl,
            shortCode = shortCode
        )

        repository.save(urlMapping)
        return shortCode
    }

    fun getOriginalUrl(shortCode: String): String? {
        return repository.findByShortCode(shortCode)?.originalUrl
    }

}
