package com.edapoc.customerquery.infrastructure.messaging.dto;

import java.time.Instant;

public record OwnerCreatedEvent(
        Long ownerId,
        String name,
        String email,
        String eventId,
        String correlationId,
        Instant occurredOn
) {}
