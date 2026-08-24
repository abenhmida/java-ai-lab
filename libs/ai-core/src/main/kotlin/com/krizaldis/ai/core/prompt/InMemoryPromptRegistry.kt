package com.krizaldis.ai.core.prompt

class InMemoryPromptRegistry(
    templates: List<PromptTemplate>,
) : PromptRegistry {
    private val prompts =
        templates.associateBy {
            "${it.name}:${it.version}"
        }

    override fun get(
        name: String,
        version: String,
    ): PromptTemplate =
        prompts["$name:$version"]
            ?: throw PromptException("Prompt not found: $name:$version")
}
