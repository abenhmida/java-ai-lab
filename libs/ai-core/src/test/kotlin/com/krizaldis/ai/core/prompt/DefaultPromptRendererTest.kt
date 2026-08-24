package com.krizaldis.ai.core.prompt

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DefaultPromptRendererTest {
    private val sut = DefaultPromptRenderer()

    @Test
    fun `renders variables`() {
        val template =
            PromptTemplate(
                name = "test",
                version = "v1",
                systemTemplate = "You are a {role}.",
                userTemplate = "Explain {topic}.",
            )

        val result =
            sut.render(
                template,
                PromptVariables(
                    mapOf(
                        "role" to "Kafka expert",
                        "topic" to "partitions",
                    ),
                ),
            )

        Assertions.assertEquals(
            "You are a Kafka expert.",
            result.systemPrompt,
        )
        Assertions.assertEquals(
            "Explain partitions.",
            result.userPrompt,
        )
    }

    @Test
    fun `missing variable fails`() {
        val template =
            PromptTemplate(
                name = "test",
                version = "v1",
                systemTemplate = "You are a {role}.",
                userTemplate = "Explain {topic}.",
            )

        assertThrows<PromptException> {
            sut.render(
                template,
                PromptVariables(
                    mapOf(
                        "topic" to "Kafka",
                    ),
                ),
            )
        }
    }
}
