package io.nordlab.legocity.server.system.events

import io.micronaut.discovery.event.ServiceReadyEvent
import io.micronaut.runtime.event.annotation.EventListener
import mu.KotlinLogging
import jakarta.inject.Singleton

private val logger = KotlinLogging.logger {}

/**
 * This class listens for the ServiceReadyEvent.
 *
 * It can be used to print debug info of the started application,
 * maybe print some configuration values to the log so that its
 * easy to see important values.
 *
 * Can also be used to initialize the application with data
 * if you need to call out to external systems after the
 * application has started to pre-load cache or similarly.
 */
@Singleton
class ApplicationInitializer {

    /**
     * Listen for the application has started even.
     */
    @EventListener
    fun applicationStartedUp(serviceStartedEvent: ServiceReadyEvent) {
        // add something to happen after ServiceReadyEvent here.
        logger.info { "application has started." }
    }
}