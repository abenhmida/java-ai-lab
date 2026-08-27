package com.krizaldis.ai.core.embedding

data class VectorSearchResult(
    val document: VectorDocument,
    val score: Double,
)
