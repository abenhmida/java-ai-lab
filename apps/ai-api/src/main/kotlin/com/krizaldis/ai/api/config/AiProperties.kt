package com.krizaldis.ai.api.config

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration

@ConfigurationProperties(prefix = "ai")
data class AiProperties(
    val provider: String,
    val chat: ChatProperties,
    val timeout: TimeoutProperties,
    val retry: RetryProperties,
    val openai: OpenAiProperties,
    val prompt: PromptProperties,
) {
    data class ChatProperties(
        val model: String,
        val temperature: Double,
        val maxTokens: Int,
    )

    data class TimeoutProperties(
        val connect: Duration,
        val read: Duration,
    )

    data class RetryProperties(
        val maxAttempts: Int,
        val initialBackoff: Duration,
    )

    data class OpenAiProperties(
        val baseUrl: String,
        val apiKey: String,
    )

    data class PromptProperties(
        val defaultTemplate: String,
        val defaultVersion: String,
    )
}
