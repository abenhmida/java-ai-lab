package com.krizaldis.ai.core.loading

import com.krizaldis.ai.core.embedding.Document

interface DocumentLoader {
    fun load(): List<Document>
}
