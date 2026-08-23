package com.krizaldis.ai.api.web

import com.krizaldis.ai.api.application.ChatService
import com.krizaldis.ai.contracts.ChatRequest
import com.krizaldis.ai.contracts.ChatResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/chat")
class ChatController(
    private val chatService: ChatService
) {
    @PostMapping
    fun chat(
        @RequestBody chatRequest: ChatRequest
    ): ChatResponse {
        return ChatResponse(
            answer = chatService.chat(chatRequest.message)
        )
    }
}