package com.krizaldis.ai.core.prompt

class PromptValidator {
    fun validate(template: PromptTemplate) {
        require(
            template.systemTemplate.isNotBlank(),
        ) {
            "System prompt cannot be empty"
        }

        require(
            template.userTemplate.isNotBlank(),
        ) {
            "User prompt cannot be empty"
        }

        require(
            template.name.isNotBlank(),
        ) {
            "Prompt name cannot be empty"
        }

        require(
            template.version.isNotBlank(),
        ) {
            "Prompt version cannot be empty"
        }
    }
}
