package com.krizaldis.ai.core

interface ChatModel {
    fun chat(request: ChatRequest): ChatResult
}
