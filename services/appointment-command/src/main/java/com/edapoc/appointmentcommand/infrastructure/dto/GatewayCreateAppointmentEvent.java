package com.edapoc.appointmentcommand.infrastructure.dto;

import java.time.LocalDateTime;

public record GatewayCreateAppointmentEvent(
    String traceId,
    Long petId,
    LocalDateTime appointmentDateTime
) {}
