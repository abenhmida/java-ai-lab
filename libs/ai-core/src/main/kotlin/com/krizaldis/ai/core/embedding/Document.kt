package com.krizaldis.ai.core.embedding

data class Document(
    val id: String,
    val content: String,
    val metadata: Map<String, String> = emptyMap(),
)
