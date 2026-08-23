package com.krizaldis.ai.api.config

import com.krizaldis.ai.core.ChatModel
import com.krizaldis.ai.core.FakeChatModel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AiConfiguration {

    @Bean
    fun chatModel(): ChatModel {
        return FakeChatModel()
    }
}