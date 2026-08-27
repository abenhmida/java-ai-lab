package com.krizaldis.ai.core.retreival

import com.krizaldis.ai.core.embedding.VectorSearchFilter

data class RetrievalRequest(
    val query: String,
    val topK: Int = 5,
    val minScore: Double? = null,
    val filter: VectorSearchFilter = VectorSearchFilter(),
)
