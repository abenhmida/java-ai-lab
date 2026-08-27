package com.krizaldis.ai.core.embedding

class BatchingEmbeddingModel(
    private val delegate: EmbeddingModel,
    private val batchSize: Int = 100,
) : EmbeddingModel {
    override fun embed(text: String): Embedding = delegate.embed(text)

    override fun embedBatch(texts: List<String>): List<Embedding> =
        texts.chunked(batchSize).flatMap { batch ->
            delegate.embedBatch(batch)
        }
}
