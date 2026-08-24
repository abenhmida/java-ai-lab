package com.krizaldis.ai.core.prompt

interface PromptRegistry {
    fun get(
        name: String,
        version: String,
    ): PromptTemplate
}
