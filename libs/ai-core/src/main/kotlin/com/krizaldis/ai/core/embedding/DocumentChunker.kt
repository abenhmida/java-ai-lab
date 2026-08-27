package com.krizaldis.ai.core.embedding

interface DocumentChunker {
    fun chunk(document: Document): List<DocumentChunk>
}
