package com.krizaldis.ai.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
open class HttpClientConfiguration {
    @Bean
    open fun webClient(builder: WebClient.Builder): WebClient = builder.build()
}
