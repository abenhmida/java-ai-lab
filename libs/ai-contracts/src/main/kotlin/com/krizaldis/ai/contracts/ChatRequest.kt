package com.krizaldis.ai.contracts

data class ChatRequest(
    val message: String
)

data class ChatResponse(
    val answer: String
)
