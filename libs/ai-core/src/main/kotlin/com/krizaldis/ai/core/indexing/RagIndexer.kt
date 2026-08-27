package com.krizaldis.ai.core.indexing

import com.krizaldis.ai.core.embedding.Document
import com.krizaldis.ai.core.embedding.DocumentChunker
import com.krizaldis.ai.core.embedding.EmbeddingModel
import com.krizaldis.ai.core.embedding.VectorDocument
import com.krizaldis.ai.core.embedding.VectorStore

class RagIndexer(
    private val chunker: DocumentChunker,
    private val embeddingModel: EmbeddingModel,
    private val vectorStore: VectorStore,
) {
    fun index(document: Document) {
        val chunks = chunker.chunk(document)

        val embeddings =
            embeddingModel.embedBatch(
                chunks.map {
                    it.content
                },
            )

        require(chunks.size == embeddings.size)

        chunks.zip(embeddings).forEach { (chunk, embedding) ->
            vectorStore.add(
                VectorDocument(
                    id = chunk.id,
                    content = chunk.content,
                    vector = embedding.vector,
                    metadata =
                        chunk.metadata +
                            mapOf(
                                "documentId" to
                                    chunk.documentId,
                                "chunkIndex" to
                                    chunk.chunkIndex.toString(),
                            ),
                ),
            )
        }
    }
}
