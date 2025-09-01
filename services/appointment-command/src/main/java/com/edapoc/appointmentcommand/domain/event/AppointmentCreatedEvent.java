package com.edapoc.appointmentcommand.domain.event;

import java.time.LocalDateTime;

public record AppointmentCreatedEvent(
  Long appointmentId,
  Long petId,
  LocalDateTime appointmentDateTime
) {}