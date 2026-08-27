package com.krizaldis.ai.core

import com.krizaldis.ai.core.retreival.RetrievedChunk

data class RagAnswer(
    val answer: String,
    val sources: List<RetrievedChunk>,
)
