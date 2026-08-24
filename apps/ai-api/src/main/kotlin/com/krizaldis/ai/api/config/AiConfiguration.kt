package com.krizaldis.ai.api.config

import com.krizaldis.ai.api.infrastructure.llm.openai.OpenAiChatModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class AiConfiguration {
    @Bean
    fun chatModel(
        @Autowired properties: AiProperties,
        @Autowired webClient: WebClient,
    ): OpenAiChatModel = OpenAiChatModel(webClient, properties)
}
