package com.krizaldis.ai.core.embedding

data class DocumentChunk(
    val id: String,
    val documentId: String,
    val documentVersion: Long = 1L,
    val chunkIndex: Int,
    val content: String,
    val metadata: Map<String, String> = emptyMap(),
)
