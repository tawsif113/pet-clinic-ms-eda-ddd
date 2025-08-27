package com.bracits.sharedevent.event;

import java.time.Instant;

public interface DomainEvent {

  String eventId();

  Instant occurredAt();

  String type();
}
