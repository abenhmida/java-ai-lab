package com.krizaldis.ai.api.application

import com.krizaldis.ai.core.ChatMessage
import com.krizaldis.ai.core.ChatModel
import com.krizaldis.ai.core.ChatRequest
import com.krizaldis.ai.core.ChatResult
import com.krizaldis.ai.core.Role
import com.krizaldis.ai.core.TokenUsage
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val chatModel: ChatModel,
) {
    fun chat(message: String): ChatResult {
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

        return chatModel.chat(request).also {
            ChatResult(
                content = it.content,
                model = it.model,
                usage =
                    it.usage?.let {
                        TokenUsage(
                            inputTokens = it.inputTokens,
                            outputTokens = it.outputTokens,
                            totalTokens = it.totalTokens,
                        )
                    },
            )
        }
    }
}
