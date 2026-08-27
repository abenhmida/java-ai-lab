package com.krizaldis.ai.core.prompt

import com.krizaldis.ai.core.context.Context

class RagPromptBuilder {
    fun build(
        question: String,
        context: Context,
    ): String {
        val sources =
            context.chunks
                .mapIndexed { index, chunk ->
                    """
                    [SOURCE ${index + 1}]
                    ${chunk.content}
                    """.trimIndent()
                }.joinToString("\n\n")

        return """
            You are a technical assistant.

            Answer the user's question using only
            the provided sources.

            If the sources do not contain enough
            information, say that you don't have
            enough information.

            Do not invent facts.

            Question:
            $question

            Sources:
            $sources

            Provide a concise technical answer.
            Cite the relevant source numbers.
            """.trimIndent()
    }
}
