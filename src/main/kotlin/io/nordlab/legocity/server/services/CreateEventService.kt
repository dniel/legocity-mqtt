package io.nordlab.legocity.server.services

import io.micronaut.context.annotation.Value
import io.micronaut.context.event.ApplicationEventPublisher
import io.nordlab.legocity.server.domain.ChangeEvent
import io.nordlab.legocity.server.domain.ChangeEventRepository
import io.nordlab.legocity.server.system.mqtt.MqttPublisher
import mu.KotlinLogging
import jakarta.inject.Named
import jakarta.inject.Singleton

private val logger = KotlinLogging.logger {}

/**
 * ## Create a new change event in the repository.
 *
 * As a side effect the repository will broadcast
 * the newly created ChangeEvent on the internal
 * micronaut EventPublisher bus which the
 * ChangeController listens for.
 */
@Singleton
@Named("create_event")
class CreateEventServiceImpl(
    val repo: ChangeEventRepository,
    val eventPublisher: ApplicationEventPublisher<ChangeEvent>,
    val mqttPublisher: MqttPublisher,

    @Value("\${mqtt.enabled:false}")
    val mqttEnabled: Boolean
) : CreateEventService {

    override suspend fun createEvent(): ChangeEvent {
        // create new event containing a random string
        val event = repo.create()

        // publish event to internal event bus
        eventPublisher.publishEventAsync(event)

        // publish event to mqtt broker if enabled
        if (mqttEnabled) {
            mqttPublisher.publishEvent(event)
        } else {
            logger.warn { "MQTT is disabled. Not publishing event." }
        }

        return event
    }
}

interface CreateEventService {
    suspend fun createEvent(): ChangeEvent
}