package com.krizaldis.ai.core

interface ChatModel {
    fun chat(prompt: String): String
}