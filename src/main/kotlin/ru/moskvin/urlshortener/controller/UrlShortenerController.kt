package ru.moskvin.urlshortener.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.moskvin.urlshortener.dto.ShortenRequest
import ru.moskvin.urlshortener.dto.ShortenResponse
import ru.moskvin.urlshortener.exception.ShortUrlNotFoundException
import ru.moskvin.urlshortener.service.UrlShortenerService

@RestController
class UrlShortenerController(
    private val urlShortenerService: UrlShortenerService
) {

    @PostMapping("/api/shorten")
    @ResponseStatus(HttpStatus.CREATED)
    fun shortenUrl(@Valid @RequestBody request: ShortenRequest): ShortenResponse {
        val shortCode = urlShortenerService.createShortUrl(request.url)
        val shortUrl = "http://localhost:8080/$shortCode"
        return ShortenResponse(shortUrl)
    }

    @GetMapping("/{shortCode}")
    fun redirectToOriginalUrl(@PathVariable shortCode: String): String {
        val originalUrl = urlShortenerService.getOriginalUrl(shortCode)
            ?: throw ShortUrlNotFoundException("Short code not found: $shortCode")

        return "redirect:$originalUrl"
    }

}
