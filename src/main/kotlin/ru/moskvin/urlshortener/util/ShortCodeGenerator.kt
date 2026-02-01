package ru.moskvin.urlshortener.util

import java.security.SecureRandom

object ShortCodeGenerator {

    private const val LENGTH = 6
    private const val CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"

    private val random = SecureRandom()

    fun generate(): String {
        return (1..LENGTH)
            .map { CHARACTERS[random.nextInt(CHARACTERS.length)] }
            .joinToString("")
    }
}