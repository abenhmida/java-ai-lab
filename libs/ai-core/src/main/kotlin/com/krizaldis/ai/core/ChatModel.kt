package com.krizaldis.ai.core

interface ChatModel {
    fun chat(request: ChatRequest): ChatResult

    fun generate(chatRequest: ChatRequest): ChatResult = chat(chatRequest)
}
