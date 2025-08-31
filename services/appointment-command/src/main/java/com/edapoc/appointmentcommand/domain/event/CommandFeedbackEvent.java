package com.edapoc.appointmentcommand.domain.event;

import java.util.UUID;

public record CommandFeedbackEvent(
    String traceId,
    String commandType,
    String status, // SUCCESS | FAILURE
    String message,
    Long appointmentId
) {}
