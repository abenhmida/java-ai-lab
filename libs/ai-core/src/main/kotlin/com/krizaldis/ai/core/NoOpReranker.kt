package com.krizaldis.ai.core

import com.krizaldis.ai.core.retreival.RetrievedChunk

class NoOpReranker : Reranker {
    override fun rerank(
        query: String,
        candidates: List<RetrievedChunk>,
        topK: Int,
    ): List<RetrievedChunk> = candidates.take(topK)
}
