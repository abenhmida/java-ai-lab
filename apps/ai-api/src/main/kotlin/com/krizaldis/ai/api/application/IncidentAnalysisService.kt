package com.krizaldis.ai.api.application

import com.krizaldis.ai.api.domain.incident.IncidentAnalysis
import com.krizaldis.ai.core.ChatMessage
import com.krizaldis.ai.core.ChatModel
import com.krizaldis.ai.core.ChatRequest
import com.krizaldis.ai.core.Role
import com.krizaldis.ai.core.prompt.PromptRegistry
import com.krizaldis.ai.core.prompt.PromptRenderer
import com.krizaldis.ai.core.prompt.PromptVariables
import com.krizaldis.ai.core.structured.StructuredOutputParser
import jakarta.validation.Validator
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
open class IncidentAnalysisService(
    private val chatModel: ChatModel,
    private val promptRegistry: PromptRegistry,
    private val promptRenderer: PromptRenderer,
    private val parser: StructuredOutputParser,
    private val validator: Validator,
) {
    fun analyze(incident: String): IncidentAnalysis {
        val template =
            promptRegistry.get(
                "incident-analyzer",
                "v1",
            )

        val rendered =
            promptRenderer.render(
                template,
                PromptVariables(
                    mapOf("incident" to incident),
                ),
            )

        val request =
            ChatRequest(
                messages =
                    listOf(
                        ChatMessage(
                            role = Role.SYSTEM,
                            content = rendered.systemPrompt,
                        ),
                        ChatMessage(
                            role = Role.USER,
                            content = rendered.userPrompt,
                        ),
                    ),
                temperature = 0.0,
            )

        val result = chatModel.chat(request)

        val analysis = parser.parse(result.content, IncidentAnalysis::class.java)

        validate(analysis)

        return analysis
    }

    private fun validate(analysis: IncidentAnalysis) {
        val violations = validator.validate(analysis)

        if (violations.isNotEmpty()) {
            val message =
                violations.joinToString("; ") {
                    "${it.propertyPath}: ${it.message}"
                }

            throw IllegalArgumentException(
                "Invalid AI output: $message",
            )
        }
    }
}
