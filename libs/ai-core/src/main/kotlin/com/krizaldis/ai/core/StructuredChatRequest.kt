package com.krizaldis.ai.core

data class StructuredChatRequest<T : Any>(
    val request: ChatRequest,
    val responseType: Class<T>,
)
