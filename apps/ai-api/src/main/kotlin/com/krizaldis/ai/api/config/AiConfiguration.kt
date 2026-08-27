package com.krizaldis.ai.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.krizaldis.ai.api.application.ChatService
import com.krizaldis.ai.api.application.DocumentIndexingService
import com.krizaldis.ai.api.application.RagService
import com.krizaldis.ai.api.application.SemanticSearchService
import com.krizaldis.ai.api.infrastructure.llm.openai.OpenAiChatModel
import com.krizaldis.ai.core.ChatModel
import com.krizaldis.ai.core.context.ContextBuilder
import com.krizaldis.ai.core.context.ContextPolicy
import com.krizaldis.ai.core.embedding.DocumentChunker
import com.krizaldis.ai.core.embedding.EmbeddingModel
import com.krizaldis.ai.core.embedding.FakeEmbeddingModel
import com.krizaldis.ai.core.embedding.InMemoryVectorStore
import com.krizaldis.ai.core.embedding.MarkdownChunker
import com.krizaldis.ai.core.embedding.VectorStore
import com.krizaldis.ai.core.indexing.RagIndexer
import com.krizaldis.ai.core.prompt.DefaultPromptRenderer
import com.krizaldis.ai.core.prompt.PromptRegistry
import com.krizaldis.ai.core.prompt.PromptRenderer
import com.krizaldis.ai.core.prompt.RagPromptBuilder
import com.krizaldis.ai.core.retreival.Retriever
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

    @Bean
    open fun embeddingModel(): EmbeddingModel = FakeEmbeddingModel()

    @Bean
    open fun vectorStore(): VectorStore = InMemoryVectorStore()

    @Bean
    open fun documentChunker(): DocumentChunker = MarkdownChunker()

    @Bean
    open fun contextPolicy(): ContextPolicy = ContextPolicy()

    @Bean
    open fun contextBuilder(contextPolicy: ContextPolicy): ContextBuilder = ContextBuilder(contextPolicy)

    @Bean
    open fun ragPromptBuilder(): RagPromptBuilder = RagPromptBuilder()

    @Bean
    open fun retriever(
        embeddingModel: EmbeddingModel,
        vectorStore: VectorStore,
    ): Retriever = Retriever(embeddingModel, vectorStore)

    @Bean
    open fun documentIndexingService(
        chunker: DocumentChunker,
        embeddingModel: EmbeddingModel,
        vectorStore: VectorStore,
    ): DocumentIndexingService = DocumentIndexingService(chunker, embeddingModel, vectorStore)

    @Bean
    open fun semanticSearchService(
        embeddingModel: EmbeddingModel,
        vectorStore: VectorStore,
    ): SemanticSearchService = SemanticSearchService(embeddingModel, vectorStore)

    @Bean
    open fun ragIndexer(
        chunker: DocumentChunker,
        embeddingModel: EmbeddingModel,
        vectorStore: VectorStore,
    ): RagIndexer = RagIndexer(chunker, embeddingModel, vectorStore)

    @Bean
    open fun ragService(
        retriever: Retriever,
        contextBuilder: ContextBuilder,
        promptBuilder: RagPromptBuilder,
        chatModel: ChatModel,
    ): RagService = RagService(retriever, contextBuilder, promptBuilder, chatModel)
}
