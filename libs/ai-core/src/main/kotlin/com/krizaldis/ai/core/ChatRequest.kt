package com.krizaldis.ai.core

data class ChatRequest(
    val messages: List<ChatMessage>,
    val temperature: Double? = null,
    val maxToken: Int? = null,
    val responseFormat: ResponseFormat = ResponseFormat.TEXT,
)

data class ChatResult(
    val content: String,
    val model: String,
    val usage: TokenUsage?,
) {
    val text: String get() = content
}

data class TokenUsage(
    val inputTokens: Int,
    val outputTokens: Int,
    val totalTokens: Int,
)
