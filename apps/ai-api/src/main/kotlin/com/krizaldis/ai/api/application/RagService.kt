package com.krizaldis.ai.api.application

import com.krizaldis.ai.core.ChatMessage
import com.krizaldis.ai.core.ChatModel
import com.krizaldis.ai.core.ChatRequest
import com.krizaldis.ai.core.RagAnswer
import com.krizaldis.ai.core.Reranker
import com.krizaldis.ai.core.context.ContextBuilder
import com.krizaldis.ai.core.prompt.RagPromptBuilder
import com.krizaldis.ai.core.retreival.RetrievalRequest
import com.krizaldis.ai.core.retreival.Retriever
import com.krizaldis.ai.core.user

class RagService(
    private val retriever: Retriever,
    private val reranker: Reranker,
    private val contextBuilder: ContextBuilder,
    private val promptBuilder: RagPromptBuilder,
    private val chatModel: ChatModel,
) {
    fun answer(question: String): RagAnswer {
        val candidates =
            retriever.retrieve(
                RetrievalRequest(question, topK = 20),
            )

        val ranked =
            reranker.rerank(
                query = question,
                candidates = candidates,
                topK = 5,
            )

        val context = contextBuilder.build(ranked)

        val prompt =
            promptBuilder.build(
                question = question,
                context = context,
            )

        val response =
            chatModel.generate(
                ChatRequest(
                    messages =
                        listOf(
                            ChatMessage.user(prompt),
                        ),
                ),
            )

        return RagAnswer(
            answer = response.text,
            sources = context.chunks,
        )
    }
}

data class SourceReference(
    val id: String,
    val source: String,
    val score: Double,
)

data class RagResponse(
    val answer: String,
    val sources: List<SourceReference>,
)

fun RagAnswer.toResponse(): RagResponse =
    RagResponse(
        answer = answer,
        sources =
            sources.map {
                SourceReference(
                    id = it.id,
                    source =
                        it.metadata["source"]
                            ?: "unknown",
                    score = it.score,
                )
            },
    )
