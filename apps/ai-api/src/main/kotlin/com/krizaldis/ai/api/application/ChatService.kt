package com.krizaldis.ai.api.application

import com.krizaldis.ai.core.ChatModel
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val chatModel: ChatModel
) {
    fun chat(message: String): String {
        return chatModel.chat(message)
    }
}