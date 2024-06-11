package io.nordlab.legocity.server.system.mqtt

import io.micronaut.mqtt.annotation.MqttSubscriber
import io.micronaut.mqtt.annotation.Topic
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

@MqttSubscriber
class MqttSubscriber {
    var event: String? = null

    @Topic("legocity/#")
    fun receive(data: String) {
        logger.info { "Received message: $data" }
        event = data
    }
}