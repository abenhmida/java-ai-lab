package com.krizaldis.ai.core.vector

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VectorMathTest {
    @Test
    fun `calculates dot product`() {
        val a =
            Vector(listOf(1.0, 2.0, 3.0))

        val b =
            Vector(listOf(4.0, 5.0, 6.0))

        assertEquals(
            32.0,
            VectorMath.dot(a, b),
        )
    }

    @Test
    fun `parallel vectors have similarity one`() {
        val a =
            Vector(listOf(1.0, 0.0))

        val b =
            Vector(listOf(10.0, 0.0))

        assertEquals(
            1.0,
            VectorMath.cosineSimilarity(a, b),
            0.000001,
        )
    }

    @Test
    fun `orthogonal vectors have similarity zero`() {
        val a =
            Vector(listOf(1.0, 0.0))

        val b =
            Vector(listOf(0.0, 1.0))

        assertEquals(
            0.0,
            VectorMath.cosineSimilarity(a, b),
            0.000001,
        )
    }

    @Test
    fun `normalizes vector`() {
        val vector =
            Vector(
                listOf(3.0, 4.0),
            )

        val normalized =
            VectorMath.normalize(vector)

        assertEquals(
            0.6,
            normalized.values[0],
            0.000001,
        )

        assertEquals(
            0.8,
            normalized.values[1],
            0.000001,
        )

        assertEquals(
            1.0,
            VectorMath.magnitude(normalized),
            0.000001,
        )
    }
}
