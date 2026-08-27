package com.krizaldis.ai.core.retreival

data class RetrievedChunk(
    val id: String,
    val content: String,
    val score: Double,
    val metadata: Map<String, String>,
)
