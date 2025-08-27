package com.bracits.commonevent.event.customer;

import java.time.Instant;

public record OwnerUpdatedEvent(
    Long ownerId,
    String name,
    String email,
    String eventId,
    String correlationId,
    Instant occurredOn
) {}
