package com.edapoc.appointmentcommand.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentCreatedEvent(
  UUID petId,
  UUID appointmentId,
  LocalDateTime appointmentDateTime
) {}