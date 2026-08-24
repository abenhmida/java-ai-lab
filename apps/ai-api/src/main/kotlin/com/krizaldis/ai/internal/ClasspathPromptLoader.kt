package com.krizaldis.ai.internal

import com.krizaldis.ai.core.prompt.PromptException
import com.krizaldis.ai.core.prompt.PromptTemplate

class ClasspathPromptLoader : PromptLoader {
    override fun load(
        name: String,
        version: String,
    ): PromptTemplate {
        val system = loadFile("prompts/$name/$version/system.txt")
        val user = loadFile("prompts/$name/$version/user.txt")

        return PromptTemplate(
            name = name,
            version = version,
            systemTemplate = system,
            userTemplate = user,
        )
    }

    private fun loadFile(path: String): String =
        javaClass.classLoader
            .getResourceAsStream(path)
            ?.bufferedReader()
            ?.use { it.readText() }
            ?: throw PromptException("Prompt resource not found: $path")
}
