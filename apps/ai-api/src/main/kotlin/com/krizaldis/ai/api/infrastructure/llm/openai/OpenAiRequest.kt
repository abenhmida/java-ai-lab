package com.krizaldis.ai.api.infrastructure.llm.openai

data class OpenAiRequest(
    val model: String,
    val messages: List<OpenAiMessage>,
    val temperature: Double? = null,
    val max_token: Int? = null,
)

class OpenAiMessage(
    val role: String,
    val content: String,
)
