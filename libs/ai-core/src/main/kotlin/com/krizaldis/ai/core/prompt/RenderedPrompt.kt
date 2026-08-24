package com.krizaldis.ai.core.prompt

data class RenderedPrompt(
    val templateName: String,
    val templateVersion: String,
    val systemPrompt: String,
    val userPrompt: String,
)
