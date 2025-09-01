package com.edapoc.appointmentcommand.application.command;

import java.time.LocalDateTime;

public record CreateAppointmentCommand(
    String correlationId,
    Long petId,
    LocalDateTime appointmentDateTime) implements Command {

}