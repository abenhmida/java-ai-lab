package com.krizaldis.ai.core.retreival

import com.krizaldis.ai.core.embedding.EmbeddingModel
import com.krizaldis.ai.core.embedding.VectorSearchRequest
import com.krizaldis.ai.core.embedding.VectorStore

class Retriever(
    private val embeddingModel: EmbeddingModel,
    private val vectorStore: VectorStore,
) {
    fun retrieve(request: RetrievalRequest): List<RetrievedChunk> {
        val queryEmbedding = embeddingModel.embed(request.query)

        return vectorStore
            .search(
                VectorSearchRequest(
                    query = queryEmbedding.vector,
                    topK = request.topK,
                    minScore = request.minScore,
                    filter = request.filter,
                ),
            ).map {
                RetrievedChunk(
                    id = it.document.id,
                    content = it.document.content,
                    score = it.score,
                    metadata = it.document.metadata,
                )
            }
    }
}
