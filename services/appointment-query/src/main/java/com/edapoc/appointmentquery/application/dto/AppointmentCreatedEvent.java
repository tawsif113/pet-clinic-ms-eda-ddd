package com.edapoc.appointmentquery.application.dto;

import java.time.LocalDateTime;

public record AppointmentCreatedEvent(
  Long appointmentId,
  Long petId,
  LocalDateTime appointmentDateTime
) {}
