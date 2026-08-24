package com.krizaldis.ai.api.application

import com.krizaldis.ai.core.ChatMessage
import com.krizaldis.ai.core.ChatModel
import com.krizaldis.ai.core.ChatRequest
import com.krizaldis.ai.core.ChatResult
import com.krizaldis.ai.core.Role
import com.krizaldis.ai.core.TokenUsage
import com.krizaldis.ai.core.prompt.PromptRegistry
import com.krizaldis.ai.core.prompt.PromptRenderer
import com.krizaldis.ai.core.prompt.PromptVariables

class ChatService(
    private val chatModel: ChatModel,
    private val promptRegistry: PromptRegistry,
    private val promptRenderer: PromptRenderer,
) {
    fun chat(message: String): ChatResult {
        val template =
            promptRegistry.get(
                "kafka-explainer",
                "v1",
            )

        val rendered =
            promptRenderer.render(
                template = template,
                variables =
                    PromptVariables(
                        mapOf(
                            "topic" to message,
                            "audience" to "senior Java developer",
                        ),
                    ),
            )

        val request =
            ChatRequest(
                messages =
                    listOf(
                        ChatMessage(
                            role = Role.SYSTEM,
                            content = rendered.systemPrompt,
                        ),
                        ChatMessage(
                            role = Role.USER,
                            content = rendered.userPrompt,
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
