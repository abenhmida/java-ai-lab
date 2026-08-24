package com.krizaldis.ai.core

class AiException(
    val type: AiErrorType,
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause)
