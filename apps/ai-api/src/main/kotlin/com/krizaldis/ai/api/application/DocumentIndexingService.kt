package com.krizaldis.ai.api.application

import com.krizaldis.ai.core.embedding.Document
import com.krizaldis.ai.core.embedding.DocumentChunker
import com.krizaldis.ai.core.embedding.EmbeddingModel
import com.krizaldis.ai.core.embedding.VectorDocument
import com.krizaldis.ai.core.embedding.VectorStore

class DocumentIndexingService(
    private val chunker: DocumentChunker,
    private val embeddingModel: EmbeddingModel,
    private val vectorStore: VectorStore,
) {
    fun index(document: Document) {
        val chunks = chunker.chunk(document)

        val embeddings = embeddingModel.embedAll(chunks.map { it.content })

        require(embeddings.size == chunks.size) {
            "Embedding count does not match chunk count"
        }

        chunks.zip(embeddings).forEach { (chunk, embedding) ->

            vectorStore.add(
                VectorDocument(
                    id = chunk.id,
                    content = chunk.content,
                    vector = embedding.vector,
                    metadata = chunk.metadata,
                ),
            )
        }
    }
}
