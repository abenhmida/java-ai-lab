package com.krizaldis.ai.core.embedding

interface EmbeddingModel {
    fun embed(text: String): Embedding

    fun embedBatch(texts: List<String>): List<Embedding>
}
