package com.krizaldis.ai.core

data class ChatMessage(
    val role: Role,
    val content: String,
)

enum class Role {
    SYSTEM,
    USER,
    ASSISTANT,
}
