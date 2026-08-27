package com.krizaldis.ai.core.trace

import com.krizaldis.ai.core.retreival.RetrievedChunk

data class RagTrace(
    val query: String,
    val retrievedChunks: List<RetrievedChunk>,
    val contextChunks: List<RetrievedChunk>,
    val answer: String,
)
