package com.krizaldis.ai.api.application

import com.krizaldis.ai.core.ChatMessage
import com.krizaldis.ai.core.ChatModel
import com.krizaldis.ai.core.ChatRequest
import com.krizaldis.ai.core.Role
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val chatModel: ChatModel,
) {
    fun chat(message: String): String {
        val request =
            ChatRequest(
                messages =
                    listOf(
                        ChatMessage(
                            role = Role.SYSTEM,
                            content =
                                """
                                You are a helpful senior software engineering assistant.
                                Give technically accurate and concise answers.
                                """.trimIndent(),
                        ),
                        ChatMessage(
                            role = Role.USER,
                            content = message,
                        ),
                    ),
            )

        return chatModel.chat(request).content
    }
}
