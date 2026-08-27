package com.krizaldis.ai.core

import com.krizaldis.ai.core.retreival.RetrievedChunk

interface Reranker {
    fun rerank(
        query: String,
        candidates: List<RetrievedChunk>,
        topK: Int,
    ): List<RetrievedChunk>
}
