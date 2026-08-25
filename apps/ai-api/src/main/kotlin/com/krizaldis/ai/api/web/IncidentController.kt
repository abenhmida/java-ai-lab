package com.krizaldis.ai.api.web

import com.krizaldis.ai.api.application.IncidentAnalysisService
import com.krizaldis.ai.api.domain.incident.IncidentAnalysis
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/incidents")
class IncidentController(
    private val service: IncidentAnalysisService,
) {
    @PostMapping("/analyze")
    fun analyze(
        @RequestBody request: AnalyzeIncidentRequest,
    ): IncidentAnalysis =
        service.analyze(
            request.incident,
        )
}
