package com.edapoc.appointmentcommand.application.command;

import java.time.LocalDateTime;

public record CreateAppointmentCommand(
    String traceId,
    Long petId,
    LocalDateTime appointmentDateTime) {
}