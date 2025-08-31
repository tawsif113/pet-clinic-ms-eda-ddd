package com.edapoc.appointmentcommand.application.command;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateAppointmentCommand(
    String traceId,
    Long petId,
    LocalDateTime appointmentDateTime) {
}