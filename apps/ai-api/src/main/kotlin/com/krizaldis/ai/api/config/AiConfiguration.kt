package com.krizaldis.ai.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.krizaldis.ai.api.application.ChatService
import com.krizaldis.ai.api.infrastructure.llm.openai.OpenAiChatModel
import com.krizaldis.ai.core.ChatModel
import com.krizaldis.ai.core.prompt.DefaultPromptRenderer
import com.krizaldis.ai.core.prompt.PromptRegistry
import com.krizaldis.ai.core.prompt.PromptRenderer
import com.krizaldis.ai.core.structured.JacksonStructuredOutputParser
import com.krizaldis.ai.internal.ClasspathPromptLoader
import com.krizaldis.ai.internal.PromptLoader
import com.krizaldis.ai.internal.ResourcePromptRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
open class AiConfiguration {
    @Bean
    open fun chatModel(
        @Autowired properties: AiProperties,
        @Autowired webClient: WebClient,
    ): OpenAiChatModel = OpenAiChatModel(webClient, properties)

    @Bean
    open fun promptLoader(): PromptLoader = ClasspathPromptLoader()

    @Bean
    open fun chatService(
        chatModel: ChatModel,
        promptRegistry: PromptRegistry,
        promptRenderer: PromptRenderer,
    ): ChatService = ChatService(chatModel, promptRegistry, promptRenderer)

    @Bean
    open fun promptRegistry(promptLoader: PromptLoader): PromptRegistry =
        ResourcePromptRegistry(
            loader = promptLoader,
        )

    @Bean
    open fun promptRenderer(): PromptRenderer = DefaultPromptRenderer()

    @Bean
    open fun incidentParser(objectMapper: ObjectMapper): JacksonStructuredOutputParser =
        JacksonStructuredOutputParser(
            objectMapper,
        )
}
