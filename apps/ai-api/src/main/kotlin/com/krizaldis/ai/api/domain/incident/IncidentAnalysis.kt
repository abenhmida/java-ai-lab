package com.krizaldis.ai.api.domain.incident

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

// @formatter:off
data class IncidentAnalysis(
    @field:NotBlank
    @field:Size(max = 200)
    val title: String,

    val severity: IncidentSeverity,
    val category: IncidentCategory,

    @field:NotBlank
    @field:Size(max = 5000)
    val summary: String,

    @field:NotEmpty
    @field:Size(max = 20)
    val affectedComponents: List<String>,

    @field:NotEmpty
    @field:Size(max = 20)
    val recommendedActions: List<String>,
)
// @formatter:on