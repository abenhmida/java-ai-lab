package com.krizaldis.ai.core.structured

interface StructuredOutputParser {
    fun <T : Any> parse(
        json: String,
        type: Class<T>,
    ): T
}
