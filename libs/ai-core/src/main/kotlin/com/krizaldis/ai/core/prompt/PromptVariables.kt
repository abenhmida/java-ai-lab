package com.krizaldis.ai.core.prompt

class PromptVariables(
    val values: Map<String, String>,
) {
    operator fun get(name: String): String? = values[name]
}
