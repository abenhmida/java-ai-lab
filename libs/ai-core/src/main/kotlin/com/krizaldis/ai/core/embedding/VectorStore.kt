package com.krizaldis.ai.core.embedding

import com.krizaldis.ai.core.vector.Vector

interface VectorStore {
    fun add(item: VectorDocument)

    fun search(request: VectorSearchRequest): List<VectorSearchResult>
}
