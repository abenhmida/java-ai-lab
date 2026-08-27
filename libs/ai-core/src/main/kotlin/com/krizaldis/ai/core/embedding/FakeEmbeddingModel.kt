package com.krizaldis.ai.core.embedding

import com.krizaldis.ai.core.vector.Vector

class FakeEmbeddingModel : EmbeddingModel {
    override fun embed(text: String): Embedding {
        val hash = text.hashCode()

        val values =
            listOf(
                ((hash and 0xFF) / 255.0),
                (((hash shr 8) and 0xFF) / 255.0),
                (((hash shr 16) and 0xFF) / 255.0),
                (((hash shr 24) and 0xFF) / 255.0),
            )

        return Embedding(
            Vector(values),
        )
    }

    override fun embedBatch(texts: List<String>): List<Embedding> = texts.map(::embed)
}
