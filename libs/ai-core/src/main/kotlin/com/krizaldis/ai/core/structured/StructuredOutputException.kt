package com.krizaldis.ai.core.structured

class StructuredOutputException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause)
