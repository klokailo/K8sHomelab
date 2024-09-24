package me.klokailo.sample_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import org.apache.logging.log4j.kotlin.logger

@SpringBootApplication
class SampleServiceApplication

private val logger = logger("SampleServiceApplication")

fun main(args: Array<String>) {
	runApplication<SampleServiceApplication>(*args)
	logger.info("Started SampleServiceApplication")
}
