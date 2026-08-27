package com.krizaldis.ai.core.vector

object VectorMath {
    fun dot(
        a: Vector,
        b: Vector,
    ): Double {
        requireSameDimension(a, b)

        return a.values
            .zip(b.values)
            .sumOf { (x, y) -> x * y }
    }

    fun magnitude(vector: Vector): Double =
        kotlin.math.sqrt(
            vector.values.sumOf { it * it },
        )

    fun cosineSimilarity(
        a: Vector,
        b: Vector,
    ): Double {
        requireSameDimension(a, b)

        val magnitudeA = magnitude(a)
        val magnitudeB = magnitude(b)

        require(magnitudeB > 0.0) {
            "Cannot compare zero vector"
        }

        return dot(a, b) / (magnitudeA * magnitudeB)
    }

    fun euclideanDistance(
        a: Vector,
        b: Vector,
    ): Double {
        requireSameDimension(a, b)

        return kotlin.math.sqrt(
            a.values
                .zip(b.values)
                .sumOf { (x, y) ->
                    val difference = x - y
                    difference * difference
                },
        )
    }

    fun normalize(vector: Vector): Vector {
        val magnitude = magnitude(vector)

        require(magnitude > 0.0) {
            "Cannot normalize zero vector"
        }

        return Vector(
            vector.values
                .map { it / magnitude },
        )
    }

    private fun requireSameDimension(
        a: Vector,
        b: Vector,
    ) {
        require(a.dimension == b.dimension) {
            "Vector dimensions differ: " +
                "${a.dimension} != ${b.dimension}"
        }
    }
}
