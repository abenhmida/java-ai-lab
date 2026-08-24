package com.krizaldis.ai.core.prompt

data class PromptTemplate(
    val name: String,
    val version: String,
    val systemTemplate: String,
    val userTemplate: String,
)
