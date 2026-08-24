package com.krizaldis.ai.core.prompt

class DefaultPromptRenderer : PromptRenderer {
    override fun render(
        template: PromptTemplate,
        variables: PromptVariables,
    ): RenderedPrompt =
        RenderedPrompt(
            templateName = template.name,
            templateVersion = template.version,
            systemPrompt = renderText(template.systemTemplate, variables),
            userPrompt = renderText(template.userTemplate, variables),
        )

    private fun renderText(
        template: String,
        variables: PromptVariables,
    ): String {
        var result = template

        Regex("""\{([a-zA-Z0-9_.-]+)}""")
            .findAll(template)
            .forEach { match ->
                val variableName = match.groupValues[1]

                val value =
                    variables[variableName]
                        ?: throw PromptException("Missing prompt variable: $variableName")

                result = result.replace(match.value, value)
            }
        return result
    }
}
