package com.krizaldis.ai.core.embedding

data class DocumentChunk(
    val id: String,
    val documentId: String,
    val content: String,
    val metadata: Map<String, String>,
)
