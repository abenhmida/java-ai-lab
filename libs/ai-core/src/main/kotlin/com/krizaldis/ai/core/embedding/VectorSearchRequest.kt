package com.krizaldis.ai.core.embedding

import com.krizaldis.ai.core.vector.Vector

data class VectorSearchRequest(
    val query: Vector,
    val topK: Int,
    val minScore: Double? = null,
    val filter: VectorSearchFilter =
        VectorSearchFilter(),
)
