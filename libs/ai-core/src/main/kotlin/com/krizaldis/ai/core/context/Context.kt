package com.krizaldis.ai.core.context

import com.krizaldis.ai.core.retreival.RetrievedChunk

data class Context(
    val chunks: List<RetrievedChunk>,
)
