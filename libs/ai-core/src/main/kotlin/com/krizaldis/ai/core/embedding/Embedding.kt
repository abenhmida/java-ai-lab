package com.krizaldis.ai.core.embedding

import com.krizaldis.ai.core.vector.Vector

data class Embedding(
    val vector: Vector,
) {
    val dimension: Int
        get() = vector.dimension
}
