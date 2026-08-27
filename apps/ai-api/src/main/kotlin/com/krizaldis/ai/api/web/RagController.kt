package com.krizaldis.ai.api.web

import com.krizaldis.ai.api.application.RagResponse
import com.krizaldis.ai.api.application.RagService
import com.krizaldis.ai.api.application.toResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class RagQueryRequest(
    val question: String,
)

@RestController
@RequestMapping("/api/rag")
class RagController(
    private val ragService: RagService,
) {
    @PostMapping("/query")
    fun query(
        @RequestBody request: RagQueryRequest,
    ): RagResponse =
        ragService
            .answer(request.question)
            .toResponse()
}
