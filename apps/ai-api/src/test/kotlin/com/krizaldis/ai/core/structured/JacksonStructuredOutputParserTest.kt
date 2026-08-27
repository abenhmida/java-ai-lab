package com.krizaldis.ai.core.structured

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.krizaldis.ai.api.domain.incident.IncidentAnalysis
import com.krizaldis.ai.api.domain.incident.IncidentSeverity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class JacksonStructuredOutputParserTest {
    private val mapper = jacksonObjectMapper()

    private val parser = JacksonStructuredOutputParser(mapper)

    @Test
    fun `parses valid incident`() {
        val json =
            """
            {
              "title": "Kafka broker failure",
              "severity": "HIGH",
              "category": "KAFKA",
              "summary": "Broker is unavailable",
              "affectedComponents": [
                "Kafka broker"
              ],
              "recommendedActions": [
                "Check broker health"
              ]
            }
            """.trimIndent()

        val result =
            parser.parse(
                json,
                IncidentAnalysis::class.java,
            )

        assertEquals("Kafka broker failure", result.title)
        assertEquals(IncidentSeverity.HIGH, result.severity)
    }

    @Test
    fun `invalid severity`() {
        val json =
            """
            {
              "title": "Kafka broker failure",
              "severity": "EXTREME",
              "category": "KAFKA",
              "summary": "Broker unavailable",
              "affectedComponents": ["broker"],
              "recommendedActions": ["Check broker"]
            }
            """.trimIndent()

        assertThrows<StructuredOutputException> {
            parser.parse(
                json,
                IncidentAnalysis::class.java,
            )
        }
    }
}
