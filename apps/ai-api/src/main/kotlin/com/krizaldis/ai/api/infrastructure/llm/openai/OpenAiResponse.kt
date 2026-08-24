package com.krizaldis.ai.api.infrastructure.llm.openai

data class OpenAiResponse(
    val choices: List<Choice>,
    val model: String?,
    val usage: Usage?,
) {
    data class Choice(
        val message: Message,
    )

    data class Message(
        val role: String,
        val content: String,
    )

    data class Usage(
        val prompt_tokens: Int,
        val completion_tokens: Int,
        val total_tokens: Int,
    )
}
