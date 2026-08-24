package com.krizaldis.ai.internal

import com.krizaldis.ai.core.prompt.PromptTemplate

interface PromptLoader {
    fun load(
        name: String,
        version: String,
    ): PromptTemplate
}
