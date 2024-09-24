package me.klokailo.sample_service

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

import org.apache.logging.log4j.kotlin.logger

@RestController
class PingController {

    private val logger = logger("PingController")

    @GetMapping("/ping")
    fun ping(): String {
        logger.info("Received request on /ping")
        return "pong"
    }

}