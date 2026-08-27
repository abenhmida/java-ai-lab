package com.krizaldis.ai.core.embedding

import com.krizaldis.ai.core.vector.Vector

data class VectorDocument(
    val id: String,
    val content: String,
    val vector: Vector,
    val metadata: Map<String, String> = emptyMap(),
)
