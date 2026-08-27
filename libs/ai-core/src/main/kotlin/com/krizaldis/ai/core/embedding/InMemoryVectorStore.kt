package com.krizaldis.ai.core.embedding

import com.krizaldis.ai.core.vector.Vector
import com.krizaldis.ai.core.vector.VectorMath

class InMemoryVectorStore : VectorStore {
    private val documents = mutableListOf<VectorDocument>()

    override fun add(item: VectorDocument) {
        documents += item
    }

    override fun search(request: VectorSearchRequest): List<VectorSearchResult> {
        require(request.topK >= 1) {
            "topK must be greater than zero"
        }

        return documents
            .asSequence()
            .filter { matchesFilter(it, request.filter) }
            .map { document ->
                VectorSearchResult(
                    document = document,
                    score =
                        VectorMath.cosineSimilarity(
                            request.query,
                            document.vector,
                        ),
                )
            }.filter { result ->
                request.minScore == null || result.score >= request.minScore
            }.sortedByDescending { it.score }
            .take(request.topK)
            .toList()
    }

    private fun matchesFilter(
        document: VectorDocument,
        filter: VectorSearchFilter,
    ) = filter.metadata.all { (key, expectedValue) ->
        document.metadata[key] == expectedValue
    }
}
