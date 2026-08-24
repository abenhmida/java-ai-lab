package com.krizaldis.ai.api

import com.krizaldis.ai.api.config.AiProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AiProperties::class)
class AiApiApplication

fun main(args: Array<String>) {
    runApplication<AiApiApplication>(*args)
}
