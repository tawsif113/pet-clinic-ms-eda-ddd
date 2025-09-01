package com.bracits.sharedevent.event.customer;

public record OwnerCreateRequestEvent(
        String name,
        String email,
        String correlationId
) {
}
