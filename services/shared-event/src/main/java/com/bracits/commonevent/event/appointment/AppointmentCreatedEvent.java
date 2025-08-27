package com.bracits.commonevent.event.appointment;

import com.bracits.commonevent.event.DomainEvent;
import java.time.Instant;

public record AppointmentCreatedEvent(
    String appointmentId,
    String ownerId,
    String petId,
    String vetId,
    String dateTimeIso,
    Instant occurredAt,
    String eventId,
    String type
) implements DomainEvent {

  public AppointmentCreatedEvent(String appointmentId, String ownerId, String petId, String vetId,
      String dateTimeIso) {
    this(appointmentId, ownerId, petId, vetId, dateTimeIso, Instant.now(),
        java.util.UUID.randomUUID().toString(), "AppointmentCreatedEvent");
  }

  @Override
  public String eventId() {
    return eventId;
  }

  @Override
  public Instant occurredAt() {
    return occurredAt;
  }

  @Override
  public String type() {
    return type;
  }
}
