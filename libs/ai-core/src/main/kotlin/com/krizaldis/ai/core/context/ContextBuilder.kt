package com.krizaldis.ai.core.context

import com.krizaldis.ai.core.retreival.RetrievedChunk

class ContextBuilder(
    private val policy: ContextPolicy,
) {
    fun build(chunks: List<RetrievedChunk>): Context {
        val selected = mutableListOf<RetrievedChunk>()

        var size = 0

        for (chunk in chunks) {
            val chunkSize = chunk.content.length

            if (chunkSize + size > policy.maxCharacters) {
                break
            }

            selected += chunk
            size += chunkSize
        }

        return Context(selected)
    }
}
