package com.krizaldis.ai.api.infrastructure.llm.openai

import com.krizaldis.ai.api.config.AiProperties
import com.krizaldis.ai.core.ChatModel
import com.krizaldis.ai.core.ChatRequest
import com.krizaldis.ai.core.ChatResult
import com.krizaldis.ai.core.TokenUsage
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class OpenAiChatModel(
    private val webClient: WebClient,
    private val properties: AiProperties,
) : ChatModel {
    override fun chat(request: ChatRequest): ChatResult {
        val providerRequest =
            OpenAiRequest(
                model = properties.chat.model,
                messages =
                    request.messages.map {
                        OpenAiMessage(
                            role = it.role.name.lowercase(),
                            content = it.content,
                        )
                    },
                temperature = request.temperature ?: properties.chat.temperature,
                max_token = request.maxToken ?: properties.chat.maxTokens,
            )

        val response =
            webClient
                .post()
                .uri("${properties.openai.baseUrl}/v1/chat/completions")
                .header(
                    HttpHeaders.AUTHORIZATION,
                    "Bearer ${properties.openai.apiKey}",
                ).bodyValue(providerRequest)
                .retrieve()
                .bodyToMono<OpenAiResponse>()
                .block()
                ?: error("Empty LLM response")

        val choice = response.choices.firstOrNull() ?: error("LLM returned no choices")

        return ChatResult(
            content = choice.message.content,
            model = response.model ?: properties.chat.model,
            usage =
                response.usage?.let {
                    TokenUsage(
                        inputTokens = it.prompt_tokens,
                        outputTokens = it.completion_tokens,
                        totalTokens = it.total_tokens,
                    )
                },
        )
    }
}
