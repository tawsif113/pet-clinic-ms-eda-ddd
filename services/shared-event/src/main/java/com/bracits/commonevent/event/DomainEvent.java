package com.bracits.commonevent.event;

import java.time.Instant;

public interface DomainEvent {

  String eventId();

  Instant occurredAt();

  String type();
}
