package com.krizaldis.ai.core.embedding

class FixedSizeChunker(
    private val chunkSize: Int = 500,
) : DocumentChunker {
    override fun chunk(document: Document): List<DocumentChunk> {
        require(chunkSize > 0)

        return document.content
            .chunked(chunkSize)
            .mapIndexed { index, content ->
                DocumentChunk(
                    id = "${document.id}-$index",
                    documentId = document.id,
                    content = content,
                    metadata = document.metadata,
                )
            }
    }
}
