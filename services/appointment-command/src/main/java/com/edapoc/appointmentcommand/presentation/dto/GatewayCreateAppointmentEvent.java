package com.edapoc.appointmentcommand.presentation.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record GatewayCreateAppointmentEvent(
    String traceId,
    UUID petId,
    LocalDateTime appointmentDateTime
) {}
