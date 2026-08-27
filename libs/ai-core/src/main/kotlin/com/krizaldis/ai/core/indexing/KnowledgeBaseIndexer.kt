package com.krizaldis.ai.core.indexing

import com.krizaldis.ai.core.loading.DocumentLoader

class KnowledgeBaseIndexer(
    private val loader: DocumentLoader,
    private val indexer: RagIndexer,
) {
    fun indexAll() {
        loader
            .load()
            .forEach { indexer.index(it) }
    }
}
