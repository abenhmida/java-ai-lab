package com.krizaldis.ai.core.prompt

interface PromptRenderer {
    fun render(
        template: PromptTemplate,
        variables: PromptVariables,
    ): RenderedPrompt
}
