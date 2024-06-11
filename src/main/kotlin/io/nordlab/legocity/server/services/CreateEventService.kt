package io.nordlab.legocity.server.services

import io.nordlab.legocity.server.domain.ChangeEvent
import io.nordlab.legocity.server.domain.ChangeEventRepository
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
class CreateEventServiceImpl(private val repo: ChangeEventRepository) : CreateEventService {

    override suspend fun createEvent() = repo.create()
}

interface CreateEventService {
    suspend fun createEvent(): ChangeEvent
}