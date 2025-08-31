package com.bracits.sharedevent.event.customer;

import java.time.Instant;

public record PetUpdatedEvent(
        Long ownerId,
        String name,
        String email,
        String petName,
        String petSpecies,
        Long petId,
        String eventId,
        String correlationId,
        Instant occurredOn
) {
}
