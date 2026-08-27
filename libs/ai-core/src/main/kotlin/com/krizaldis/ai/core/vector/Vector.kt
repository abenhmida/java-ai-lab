package com.krizaldis.ai.core.vector

data class Vector(
    val values: List<Double>,
) {
    init {
        require(values.isNotEmpty()) {
            "Vector cannot be empty"
        }

        require(values.all { it.isFinite() }) {
            "Vector contains non-finite values"
        }
    }

    val dimension: Int
        get() = values.size
}
