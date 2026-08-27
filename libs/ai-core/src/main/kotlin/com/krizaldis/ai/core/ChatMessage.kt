package com.krizaldis.ai.core

data class ChatMessage(
    val role: Role,
    val content: String,
) {
    companion object
}

enum class Role {
    SYSTEM,
    USER,
    ASSISTANT,
}

fun ChatMessage.Companion.user(prompt: String): ChatMessage = ChatMessage(role = Role.USER, content = prompt)
