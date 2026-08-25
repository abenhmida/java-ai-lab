package com.krizaldis.ai.core.structured

import com.fasterxml.jackson.databind.ObjectMapper

class JacksonStructuredOutputParser(
    private val objectMapper: ObjectMapper,
) : StructuredOutputParser {
    override fun <T : Any> parse(
        json: String,
        type: Class<T>,
    ): T =
        try {
            objectMapper.readValue(json, type)
        } catch (ex: Exception) {
            throw StructuredOutputException(
                "Unable to parse structured AI output",
                ex,
            )
        }
}
