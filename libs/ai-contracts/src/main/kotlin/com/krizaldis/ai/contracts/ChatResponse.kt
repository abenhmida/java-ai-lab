package com.krizaldis.ai.contracts

data class ChatResponse(
    val answer: String,
    val model: String,
    val usage: Usage?,
) {
    class Usage(
        val inputTokens: Int,
        val outputTokens: Int,
        val totalTokens: Int,
    )
}
