package com.krizaldis.ai.internal

import com.krizaldis.ai.core.prompt.PromptRegistry
import com.krizaldis.ai.core.prompt.PromptTemplate

class ResourcePromptRegistry(
    private val loader: PromptLoader,
) : PromptRegistry {
    override fun get(
        name: String,
        version: String,
    ): PromptTemplate = loader.load(name, version)
}
