package com.krizaldis.ai.api.application

import com.krizaldis.ai.core.embedding.EmbeddingModel
import com.krizaldis.ai.core.embedding.VectorSearchFilter
import com.krizaldis.ai.core.embedding.VectorSearchRequest
import com.krizaldis.ai.core.embedding.VectorSearchResult
import com.krizaldis.ai.core.embedding.VectorStore

class SemanticSearchService(
    private val embeddingModel: EmbeddingModel,
    private val vectorStore: VectorStore,
) {
    fun search(
        query: String,
        topK: Int,
        filter: VectorSearchFilter = VectorSearchFilter(),
    ): List<VectorSearchResult> {
        val embedding = embeddingModel.embed(query)

        return vectorStore.search(
            VectorSearchRequest(
                topK = topK,
                query = embedding.vector,
                filter = VectorSearchFilter(),
            ),
        )
    }
}
