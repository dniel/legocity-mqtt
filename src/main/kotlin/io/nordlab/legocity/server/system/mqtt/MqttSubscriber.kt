package io.nordlab.legocity.server.system.mqtt

import io.micronaut.mqtt.annotation.MqttSubscriber
import io.micronaut.mqtt.annotation.Topic

@MqttSubscriber
class MqttSubscriber {
    var event: String? = null

    @Topic("legocity/#")
    fun receive(data: String) {
        event = data
    }
}