package com.bracits.sharedevent.event.customer;

import java.time.Instant;

public record PetCreatedEvent(
    Long ownerId,
    String name,
    String email,
    Long petId,
    String eventId,
    String correlationId,
    Instant occurredOn,
    String petName
) {

}
